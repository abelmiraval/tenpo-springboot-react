import type {TransactionForm, TransactionResponse} from "../models/transaction.model.ts";

export interface TransactionState {
    transactions: TransactionResponse[];
    filteredTransactions: TransactionResponse[];
    isEditing: boolean;
    editingTransaction: TransactionForm | null;
    filterCategory: string;
    isLoading: boolean;
}

export const initialState: TransactionState = {
    transactions: [],
    filteredTransactions: [],
    isEditing: false,
    editingTransaction: null,
    filterCategory: '',
    isLoading: false,
};


type TransactionAction =
    | { type: 'SET_TRANSACTIONS'; payload: TransactionResponse[] }
    | { type: 'ADD_TRANSACTION'; }
    | { type: 'UPDATE_TRANSACTION'; }
    | { type: 'DELETE_TRANSACTION'; }
    | { type: 'SET_EDITING_TRANSACTION'; payload: TransactionForm }
    | { type: 'FILTER_BY_CATEGORY'; payload: string }
    | { type: 'RESET_FORM' }

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
                isEditing: false
            };
        case 'UPDATE_TRANSACTION':
            return {
                ...state,
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
                isEditing: false,
                editingTransaction: null,
            };

        default:
            return state;
    }
};