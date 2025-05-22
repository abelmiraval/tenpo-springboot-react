import { createContext } from "react";
import type {TransactionForm} from "../models/transaction.model";
import type { TransactionState } from "./transaction.reducer";

interface TransactionContextType {
    state: TransactionState;
    addTransaction: (transaction: TransactionForm) => Promise<boolean>;
    updateTransaction: (transaction: TransactionForm) => Promise<boolean>;
    deleteTransaction: (id: number) => Promise<boolean>;
    setEditingTransaction: (transaction: TransactionForm) => Promise<void>;
    filterByCategory: (category: string) => void;
    resetForm: () => void;
}

const defaultContext: TransactionContextType = {
    state: {
        transactions: [],
        filteredTransactions: [],
        isEditing: false,
        editingTransaction: null,
        filterCategory: '',
        isLoading: false,
    },
    addTransaction: async () => false,
    updateTransaction: async () => false,
    deleteTransaction: async () => false,
    setEditingTransaction: async () => {},
    filterByCategory: () => {},
    resetForm: () => {},
};

export const TransactionContext = createContext<TransactionContextType>(defaultContext);