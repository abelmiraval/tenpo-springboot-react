import React from 'react';
import TransactionTable from './transaction-table.component';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {TransactionProvider} from "../context/transaction.provider.tsx";
import TransactionForm from './transaction-form.component.tsx';

const TransactionList: React.FC = () => {
    return (
        <TransactionProvider>
            <div className="p-4 max-w-6xl mx-auto bg-tenpo-neutral-100">
                <h1 className="text-2xl font-bold mb-6 text-center text-tenpo-neutral-800">
                    Transaction Management System
                </h1>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <TransactionForm />
                    <TransactionTable />
                </div>

                <ToastContainer
                    position="bottom-right"
                    autoClose={3000}
                    hideProgressBar={false}
                    newestOnTop
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover
                />
            </div>
        </TransactionProvider>
    );
};

export default TransactionList;