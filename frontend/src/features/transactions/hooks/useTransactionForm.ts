import {useContext, useEffect, useState} from "react";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {TransactionContext} from "../context/transaction.context";
import {
    transactionSchema,
    updateTransactionSchema,
    type TransactionFormData,
    type UpdateTransactionFormData,
    TRANSACTION_CATEGORIES
} from "../schemas/transaction.schema";
import {toast} from "react-toastify";

const useTransactionForm = () => {
    const {
        state,
        addTransaction,
        updateTransaction,
        resetForm,
    } = useContext(TransactionContext);

    const [isSubmitting, setIsSubmitting] = useState(false);
    const isEditing = state.isEditing && state.editingTransaction;
    const form = useForm<TransactionFormData | UpdateTransactionFormData>({
        resolver: zodResolver(isEditing ? updateTransactionSchema : transactionSchema),
        defaultValues: {
            amount: 0,
            category: "",
            username: "",
            date: new Date().toISOString().split('T')[0],
            description: "",
            ...(isEditing && {id: state.editingTransaction?.id})
        }
    });
    const {handleSubmit, formState: {errors}, reset, setValue, watch, getValues} = form;

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
            console.error("Error saving the transaction:", error);
            toast.error("Error saving the transaction");
        } finally {
            setIsSubmitting(false);
        }
    };

    const onError = (errors: any) => {
        console.error("Validation errors:", errors);
        toast.error("Please correct the errors in the form");
    };

    const resetFormData = () => {
        resetForm();
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
        handleSubmit: handleSubmit(onSubmit, onError),
        errors,
        reset,
        setValue,
        watch,
        getValues,
        isEditing,
        isSubmitting,
        isLoading: state.isLoading || isSubmitting,
        categories: TRANSACTION_CATEGORIES,
        resetFormData,
        cancelEdit,

        formValues: watch()
    };
};

export default useTransactionForm;