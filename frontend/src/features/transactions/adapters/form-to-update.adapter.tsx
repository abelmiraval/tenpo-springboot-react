import type {TransactionForm, UpdateTransaction} from "../models/transaction.model";

export default  function formToUpdateAdapter(formData: TransactionForm): UpdateTransaction {
    const { id, amount, date, category, username } = formData;

    return {
        id: id || 0,
        amount: amount,
        date: date,
        category,
        username,
    };
}