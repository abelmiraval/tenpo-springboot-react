import type {CreateTransaction, TransactionForm} from "../models/transaction.model";

export default function formToCreateAdapter(formData: TransactionForm): CreateTransaction {
    const { amount, date, category, username } = formData;

    return {
        amount: amount,
        date: date,
        category,
        username,
    };
}