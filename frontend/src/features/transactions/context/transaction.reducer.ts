import type {TransactionForm, TransactionResponse} from "../models/transaction.model.ts";

export interface TransactionState {
    transactions: TransactionResponse[];
    filteredTransactions: TransactionResponse[];
    currentTransaction: Partial<TransactionForm>;
    isEditing: boolean;
    editingTransaction: TransactionForm | null;
    selectedCategory: string;
    filterCategory: string;
    isLoading: boolean;
    formErrors: TransactionErrors;
}

export const initialState: TransactionState = {
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
};

export interface TransactionErrors {
    amount?: string;
    category?: string;
    username?: string;
    date?: string;
}

type TransactionAction =
    | { type: 'SET_TRANSACTIONS'; payload: TransactionResponse[] }
    | { type: 'ADD_TRANSACTION'; }
    | { type: 'UPDATE_TRANSACTION'; }
    | { type: 'DELETE_TRANSACTION'; }
    | { type: 'SET_EDITING_TRANSACTION'; payload: TransactionForm }
    | { type: 'FILTER_BY_CATEGORY'; payload: string }
    | { type: 'RESET_FORM' }
    | { type: 'SET_FORM_ERRORS'; payload: TransactionErrors };

export const transactionReducer = (state: TransactionState, action: TransactionAction): TransactionState => {
    switch (action.type) {
        case 'SET_TRANSACTIONS':
            return {
                ...state,
                transactions: action.payload,
                filteredTransactions: action.payload
            };
        case 'ADD_TRANSACTION':
            return {
                ...state,
                currentTransaction: initialState.currentTransaction,
                isEditing: false
            };
        case 'UPDATE_TRANSACTION':
            return {
                ...state,
                currentTransaction: initialState.currentTransaction,
                isEditing: false,
                editingTransaction: null
            };
        case 'DELETE_TRANSACTION':
            return {
                ...state,
            };
        case 'SET_EDITING_TRANSACTION':
            return {
                ...state,
                currentTransaction: action.payload,
                isEditing: true,
                editingTransaction: action.payload
            };
        case 'FILTER_BY_CATEGORY':
            return {
                ...state,
                filterCategory: action.payload,
            };
        case 'RESET_FORM':
            return {
                ...state,
                currentTransaction: initialState.currentTransaction,
                isEditing: false,
                editingTransaction: null,
                formErrors: {}
            };
        case 'SET_FORM_ERRORS':
            return {
                ...state,
                formErrors: action.payload
            };
        default:
            return state;
    }
};