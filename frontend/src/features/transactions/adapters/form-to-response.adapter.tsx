import {formatDateForInput} from "../../../utils/formatters";
import type {TransactionForm, TransactionResponse} from "../models/transaction.model";

export default function responseToFormAdapter(response: TransactionResponse): TransactionForm {
    const { id, amount, date, category, username } = response;

    return {
        id: id || 0,
        amount: amount,
        date: formatDateForInput(date),
        category,
        username,
    };
}