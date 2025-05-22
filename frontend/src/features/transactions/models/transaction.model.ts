
export interface BaseResponse<T> {
    data: T;
    message: string;
    success: boolean;
    errors: string[];
}

export interface CreateTransaction {
    amount: number;
    category: string;
    username: string;
    date: string;
}

export interface UpdateTransaction {
    id: number;
    amount: number;
    category: string;
    username: string;
    date: string;
}

export interface TransactionResponse{
    id: number;
    amount: number;
    category: string;
    username: string;
    date: string;
}

export interface TransactionForm {
    id?: number;
    amount: number;
    category: string;
    username: string;
    date: string;
}
