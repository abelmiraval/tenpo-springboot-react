import React from 'react';
import Select from '../../../components/common/select/select.component';
import useTransactionFilter from '../hooks/useTransactionFilter';

const TransactionFilter: React.FC = () => {
    const {
        availableCategories,
        currentFilter,
        applyFilter,
    } = useTransactionFilter();

    const handleFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const category = e.target.value;
        applyFilter(category);
    };

    return (
        <div className="flex items-center gap-3">
            <div className="flex items-center gap-2">
                <Select
                    name="filter"
                    value={currentFilter}
                    onChange={handleFilterChange}
                    options={availableCategories}
                    placeholder="Filter by category"
                    className="w-48"
                />
            </div>
        </div>
    );
};

export default TransactionFilter;