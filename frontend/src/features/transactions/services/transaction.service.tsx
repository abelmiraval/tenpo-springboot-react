import axios from "../../../lib/api";
import type {BaseResponse, CreateTransaction, TransactionResponse, UpdateTransaction} from "../models/transaction.model";

const RESOURCE = '/transactions';

export const transactionService = {
    async getAll(): Promise<BaseResponse<TransactionResponse[]>> {
        const { data } = await axios.get<BaseResponse<TransactionResponse[]>>(RESOURCE);
        return data;
    },

    async getById(id: number): Promise<BaseResponse<TransactionResponse>> {
        const { data } = await axios.get<BaseResponse<TransactionResponse>>(`${RESOURCE}/${id}`);
        return data;
    },

    async create(transaction: CreateTransaction): Promise<BaseResponse<boolean>> {
        const { data } = await axios.post<BaseResponse<boolean>>(RESOURCE, transaction);
        return data;
    },

    async update(transaction: UpdateTransaction): Promise<BaseResponse<boolean>> {
        const { data } = await axios.put<BaseResponse<boolean>>(RESOURCE, transaction);
        return data;
    },

    async delete(id: string): Promise<void> {
        await axios.delete(`${RESOURCE}/${id}`);
    },
};
