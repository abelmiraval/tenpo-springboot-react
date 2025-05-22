import { useContext, useMemo, useCallback } from 'react';
import { TransactionContext } from '../context/transaction.context';

export interface FilterOption {
    value: string;
    label: string;
}

const useTransactionFilter = () => {
    const { state, filterByCategory } = useContext(TransactionContext);
    const { transactions, filterCategory } = state;

    const options: FilterOption[] = useMemo(() => [
        { value: 'all', label: 'All categories' },
        { value: 'salary', label: 'Salary' },
        { value: 'food', label: 'Food' },
        { value: 'transport', label: 'Transport' },
        { value: 'education', label: 'Education' },
        { value: 'health', label: 'Health' },
        { value: 'other', label: 'Other' }
    ], []);

    const filteredTransactions = useMemo(() => {
        if (!filterCategory || filterCategory === 'all' || filterCategory === '') {
            return transactions;
        }
        return transactions.filter(transaction => transaction.category === filterCategory);
    }, [transactions, filterCategory]);

    const availableCategories = useMemo(() => {
        const uniqueCategories = [...new Set(transactions.map(t => t.category))];
        return options.filter(option =>
            option.value === 'all' || uniqueCategories.includes(option.value)
        );
    }, [transactions, options]);


    const applyFilter = useCallback((category: string) => {
        filterByCategory(category);
    }, [filterByCategory]);

    return {
        filteredTransactions,
        availableCategories,
        currentFilter: filterCategory || 'all',
        applyFilter
    };
};

export default useTransactionFilter;