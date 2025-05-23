import React, {useContext} from 'react';
import {Edit2, Trash2} from 'lucide-react';
import {formatCurrency, formatDateToLocal} from '../../../utils/formatters';
import Card from "../../../components/common/card/card.component";
import TransactionFilter from './transaction-filter.component';
import useTransaction from "../hooks/useTransaction";
import {TransactionContext} from "../context/transaction.context.tsx";
import useTransactionFilter from "../hooks/useTransactionFilter";
import useTransactionForm from "../hooks/useTransactionForm";

const TransactionTable: React.FC = () => {
    const {
        totalBalance,
        isLoading
    } = useTransaction();
    const {filteredTransactions} = useTransactionFilter()
    const {handleDelete} = useTransactionForm();
    const {setEditingTransaction} = useContext(TransactionContext);

    const onClickEdit = async (transactionId: number) => {
        const transaction = filteredTransactions.find(t => t.id === transactionId);
        if (transaction) {
            await setEditingTransaction(transaction);
        }
    };

    const onClickDelete = async (id: number) => {
        if (window.confirm("Are you sure you want to delete this transaction?")) {
            await handleDelete(id);
        }
    };

    return (
        <div>
            <Card className="mb-4">
                <h2 className="text-xl font-semibold mb-2 text-tenpo-neutral-800">Balance Total</h2>
                <p className={`text-2xl font-bold ${totalBalance >= 0 ? 'text-tenpo-primary' : 'text-tenpo-error'}`}>
                    {formatCurrency(totalBalance)}
                </p>
            </Card>

            <Card>
                <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center mb-4 gap-2">
                    <h2 className="text-xl font-semibold text-tenpo-neutral-800">Transactions</h2>
                    <TransactionFilter/>
                </div>

                {isLoading ? (
                    <div className="flex justify-center items-center py-8">
                        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-tenpo-primary"></div>
                        <span className="ml-2 text-tenpo-neutral-600">Loading Transactions...</span>
                    </div>
                ) : filteredTransactions.length > 0 ? (
                    <div className="overflow-x-auto">
                        <table className="min-w-full bg-tenpo-neutral-50">
                            <thead>
                            <tr className="bg-tenpo-neutral-100">
                                <th className="py-2 px-4 text-left text-tenpo-neutral-800">Amount</th>
                                <th className="py-2 px-4 text-left text-tenpo-neutral-800">Category</th>
                                <th className="py-2 px-4 text-left text-tenpo-neutral-800">Date</th>
                                <th className="py-2 px-4 text-left text-tenpo-neutral-800">User</th>
                                <th className="py-2 px-4 text-center text-tenpo-neutral-800">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {filteredTransactions.map(transaction => (
                                <tr key={transaction.id} className="border-b border-tenpo-neutral-200">
                                    <td className={`py-2 px-4 ${transaction.amount >= 0 ? 'text-tenpo-primary' : 'text-tenpo-error'}`}>
                                        {formatCurrency(transaction.amount)}
                                    </td>
                                    <td className="py-2 px-4 text-tenpo-neutral-600">{transaction.category}</td>
                                    <td className="py-2 px-4 text-tenpo-neutral-600">{formatDateToLocal(transaction.date)}</td>
                                    <td className="py-2 px-4 text-tenpo-neutral-600">{transaction.username}</td>
                                    <td className="py-2 px-4">
                                        <div className="flex justify-center gap-2">
                                            <button
                                                onClick={() => onClickEdit(transaction.id)}
                                                disabled={isLoading}
                                                className="p-2 rounded-md text-tenpo-secondary hover:bg-tenpo-secondary hover:text-white transition-colors duration-200 disabled:opacity-50 disabled:cursor-not-allowed"
                                            >
                                                <Edit2 size={16}/>
                                            </button>
                                            <button
                                                onClick={() => onClickDelete(transaction.id)}
                                                disabled={isLoading}
                                                className="p-2 rounded-md text-tenpo-error hover:bg-tenpo-error hover:text-white transition-colors duration-200 disabled:opacity-50 disabled:cursor-not-allowed"
                                            >
                                                <Trash2 size={16}/>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                ) : (<p className="text-tenpo-neutral-600 italic">No transactions available.</p>)
                }
            </Card>
        </div>
    );
};

export default TransactionTable;