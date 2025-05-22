import type {TransactionForm, TransactionResponse} from "../models/transaction.model";

export default function responseToFormAdapter(response: TransactionResponse): TransactionForm {
    const { id, amount, date, category, username } = response;

    return {
        id: id || 0,
        amount: amount,
        date: date,
        category,
        username,
    };
}