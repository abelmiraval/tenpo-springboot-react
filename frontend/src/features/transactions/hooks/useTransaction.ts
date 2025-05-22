import { useContext, useMemo } from 'react';
import { TransactionContext } from '../context/transaction.context';
import type {TransactionResponse} from "../models/transaction.model";

const useTransaction = () => {
    const { state } = useContext(TransactionContext);
    const { transactions, isLoading } = state;

    const totalBalance = useMemo(() => {
        return transactions.reduce((sum: number, transaction: TransactionResponse) =>
            sum + transaction.amount, 0
        );
    }, [transactions]);


    return {
        transactions,
        totalBalance,
        isLoading
    };
};

export default useTransaction;