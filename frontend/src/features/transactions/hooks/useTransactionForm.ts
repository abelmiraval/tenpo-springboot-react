import { useContext, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { TransactionContext } from "../context/transaction.context";
import {
    transactionSchema,
    updateTransactionSchema,
    type TransactionFormData,
    type UpdateTransactionFormData,
    TRANSACTION_CATEGORIES
} from "../schemas/transaction.schema";
import { toast } from "react-toastify";

const useTransactionForm = () => {
    const {
        state,
        addTransaction,
        updateTransaction,
        resetForm,
    } = useContext(TransactionContext);

    const [isSubmitting, setIsSubmitting] = useState(false);
    // Determinar si estamos editando
    const isEditing = state.isEditing && state.editingTransaction;

    // Configurar React Hook Form con el schema apropiado
    const form = useForm<TransactionFormData | UpdateTransactionFormData>({
        resolver: zodResolver(isEditing ? updateTransactionSchema : transactionSchema),
        defaultValues: {
            amount: 0,
            category: "",
            username: "",
            date: new Date().toISOString().split('T')[0],
            description: "",
            ...(isEditing && { id: state.editingTransaction?.id })
        }
    });

    const { handleSubmit, formState: { errors }, reset, setValue, watch, getValues } = form;

    // Actualizar formulario cuando cambia la transacción en edición
    useEffect(() => {
        if (isEditing && state.editingTransaction) {
            const transaction = state.editingTransaction;
            reset({
                amount: transaction.amount || 0,
                category: transaction.category || "",
                username: transaction.username || "",
                date: transaction.date || new Date().toISOString().split('T')[0],
                id: transaction.id
            });
        }
    }, [state.editingTransaction, state.isEditing, reset, isEditing]);

    const onSubmit = async (data: TransactionFormData | UpdateTransactionFormData) => {
        setIsSubmitting(true);

        try {
            let result: boolean = false;
            if (isEditing && 'id' in data) {
                result = await updateTransaction({
                    ...data,
                    amount: Number(data.amount)
                });
            } else {
                result = await addTransaction({
                    ...data,
                    amount: Number(data.amount)
                } as TransactionFormData);
            }

            if (result) {
                resetFormData();
            }

        } catch (error) {
            console.error("Error al guardar la transacción:", error);
            toast.error("Error al guardar la transacción");
        } finally {
            setIsSubmitting(false);
        }
    };

    // Manejar errores de validación
    const onError = (errors: any) => {
        console.error("Errores de validación:", errors);
        toast.error("Por favor, corrige los errores en el formulario");
    };

    const resetFormData = () => {
        // 1. Resetear el estado del contexto (isEditing, editingTransaction, etc.)
        resetForm();

        // 2. Resetear el formulario de react-hook-form
        reset({
            amount: 0,
            category: "",
            username: "",
            date: new Date().toISOString().split('T')[0],
            description: ""
        });
    };

    const cancelEdit = () => {
        resetFormData();
    };

    return {
        // Form methods
        handleSubmit: handleSubmit(onSubmit, onError),
        errors,
        reset,
        setValue,
        watch,
        getValues,

        // Estados
        isEditing,
        isSubmitting,
        isLoading: state.isLoading || isSubmitting,

        // Categorías estáticas
        categories: TRANSACTION_CATEGORIES,

        // Acciones
        resetFormData,
        cancelEdit,

        formValues: watch()
    };
};

export default useTransactionForm;