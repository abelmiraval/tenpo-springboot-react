import React from 'react';
import useTransactionForm from '../hooks/useTransactionForm';
import Button from '../../../components/common/button/button.component';
import Card from '../../../components/common/card/card.component';
import Input from '../../../components/common/input/input.component';
import Select from '../../../components/common/select/select.component';

const TransactionForm: React.FC = () => {
    const {
        handleSubmit,
        errors,
        isEditing,
        isLoading,
        categories,
        resetFormData,
        cancelEdit,
        watch,
        setValue
    } = useTransactionForm();

    const formValues = watch();

    return (
        <Card>
            <h2 className="text-xl font-semibold mb-4 text-tenpo-neutral-800">
                {isEditing ? 'Edit Transaction' : 'New Transaction'}
            </h2>

            <form onSubmit={handleSubmit} className="space-y-4">
                <Input
                    label="Amount"
                    type="number"
                    name="amount"
                    value={formValues.amount?.toString() || ''}
                    onChange={(e) => setValue('amount', parseFloat(e.target.value) || 0)}
                    error={errors.amount?.message}
                    placeholder="0.00"
                />

                <Select
                    label="Category"
                    name="category"
                    value={formValues.category || ''}
                    onChange={(e) => setValue('category', e.target.value)}
                    options={categories}
                    placeholder="Select a category"
                    error={errors.category?.message}
                    disabled={isLoading}
                />

                <Input
                    label="Date"
                    type="date"
                    name="date"
                    value={formValues.date || ''}
                    onChange={(e) => setValue('date', e.target.value)}
                    error={errors.date?.message}
                />

                <Input
                    label="User"
                    type="text"
                    name="username"
                    value={formValues.username || ''}
                    onChange={(e) => setValue('username', e.target.value)}
                    error={errors.username?.message}
                    placeholder="User's name"
                />

                <div className="flex flex-col sm:flex-row gap-2 pt-4">
                    <Button
                        type="submit"
                        variant="primary"
                        className="flex-1"
                        disabled={isLoading}
                    >
                        {isLoading
                            ? (isEditing ? 'Updating...' : 'Saving...')
                            : (isEditing ? 'Update Transaction' : 'Create Transaction')
                        }
                    </Button>

                    {isEditing && (
                        <Button
                            type="button"
                            variant="secondary"
                            className="flex-1"
                            onClick={cancelEdit}
                            disabled={isLoading}
                        >
                            Cancel
                        </Button>
                    )}

                    {!isEditing && (
                        <Button
                            type="button"
                            variant="outline"
                            className="flex-1"
                            onClick={resetFormData}
                            disabled={isLoading}
                        >
                            Clear
                        </Button>
                    )}
                </div>
            </form>
        </Card>
    );
};

export default TransactionForm;
