import {type ReactNode, useCallback, useEffect, useReducer, useState} from "react";
import {initialState, transactionReducer} from "./transaction.reducer.ts";
import {TransactionContext} from "./transaction.context.tsx";
import {toast} from "react-toastify";
import {transactionService} from "../services/transaction.service.tsx";
import type {TransactionForm} from "../models/transaction.model";
import formToCreateAdapter from "../adapters/form-to-create.adapter";
import formToUpdateAdapter from "../adapters/form-to-update.adapter";
import responseToFormAdapter from "../adapters/form-to-response.adapter";

interface TransactionProviderProps {
    children: ReactNode;
}

export const TransactionProvider: React.FC<TransactionProviderProps> = ({children}) => {
    const [state, dispatch] = useReducer(transactionReducer, initialState);
    const [isLoading, setIsLoading] = useState(false);

    const fetchTransactions = useCallback(async () => {
        try {
            const {data: transactions} = await transactionService.getAll();
            dispatch({
                type: 'SET_TRANSACTIONS',
                payload: transactions
            });
        } catch (error) {
            console.error("Error loading transactions:", error);
            toast.error("Failed to load transactions");
        }
    }, []);

    useEffect(() => {
        const loadInitialData = async () => {
            setIsLoading(true);
            await fetchTransactions();
            setIsLoading(false);
        };

        loadInitialData();
    }, [fetchTransactions]);

    const addTransaction = async (transaction: TransactionForm) => {
        setIsLoading(true);
        try {
            const request = formToCreateAdapter(transaction);
            await transactionService.create(request);
            dispatch({type: 'ADD_TRANSACTION'});

            await fetchTransactions();

            toast.success("Transaction added successfully");

            return true
        } catch (error) {
            console.error("Error adding transaction:", error);
            toast.error("Failed to add transaction");
            return false;
        } finally {
            setIsLoading(false);
        }
    };

    const updateTransaction = async (transaction: TransactionForm) => {
        setIsLoading(true);
        try {
            const request = formToUpdateAdapter(transaction);
            await transactionService.update(request);
            dispatch({type: 'UPDATE_TRANSACTION'});

            await fetchTransactions();
            toast.success("Transaction updated successfully");
            return true;
        } catch (error) {
            console.error("Error updating transaction:", error);
            toast.error("Failed to update transaction");
            return false;
        } finally {
            setIsLoading(false);
        }
    };

    const deleteTransaction = async (id: number) => {
        setIsLoading(true);
        try {
            await transactionService.delete(id.toString());
            dispatch({type: 'DELETE_TRANSACTION'});

            await fetchTransactions();

            toast.success("Transaction deleted successfully");
            return true;
        } catch (error) {
            console.error("Error deleting transaction:", error);
            toast.error("Failed to delete transaction");
            return false;
        } finally {
            setIsLoading(false);
        }
    };

    const setEditingTransaction = async (transaction: TransactionForm) => {
        try {
            const {data: response} = await transactionService.getById(transaction.id || 0);
            const form = responseToFormAdapter(response);
            dispatch({type: 'SET_EDITING_TRANSACTION', payload: form});
        } catch (error) {
            console.error("Error getting transaction details:", error);
            toast.error("Failed to get transaction");
        }
    };

    const filterByCategory = (category: string) => {
        dispatch({type: 'FILTER_BY_CATEGORY', payload: category});
    };

    const resetForm = () => {
        dispatch({type: 'RESET_FORM'});
    };


    return (
        <TransactionContext.Provider
            value={{
                state: {
                    ...state,
                    isLoading
                },
                addTransaction,
                updateTransaction,
                deleteTransaction,
                setEditingTransaction,
                filterByCategory,
                resetForm
            }}
        >
            {children}
        </TransactionContext.Provider>
    );
};