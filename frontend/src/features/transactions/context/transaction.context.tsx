import { createContext } from "react";
import type {TransactionForm} from "../models/transaction.model";
import type { TransactionErrors, TransactionState } from "./transaction.reducer";

interface TransactionContextType {
    state: TransactionState;
    addTransaction: (transaction: TransactionForm) => Promise<void>;
    updateTransaction: (transaction: TransactionForm) => Promise<void>;
    deleteTransaction: (id: number) => Promise<void>;
    setEditingTransaction: (transaction: TransactionForm) => Promise<void>;
    filterByCategory: (category: string) => void;
    resetForm: () => void;
    validateForm: (transaction: Partial<TransactionForm>) => TransactionErrors;
}

const defaultContext: TransactionContextType = {
    state: {
        transactions: [],
        filteredTransactions: [],
        currentTransaction: {
            amount: 0,
            category: '',
            username: '',
            date: new Date().toISOString().split('T')[0]
        },
        isEditing: false,
        editingTransaction: null,
        selectedCategory: '',
        filterCategory: '',
        isLoading: false,
        formErrors: {},
    },
    addTransaction: async () => {},
    updateTransaction: async () => {},
    deleteTransaction: async () => {},
    setEditingTransaction: async () => {},
    filterByCategory: () => {},
    resetForm: () => {},
    validateForm: () => ({})
};

export const TransactionContext = createContext<TransactionContextType>(defaultContext);