import HeaderComponent from "./components/layout/header/header.component.tsx";
import TransactionList from "./features/transactions/components/transaction-administration.component.tsx";


export default function TransactionApp() {

    return (
        <div className="min-h-screen bg-gray-50">
            <HeaderComponent/>
            <main className="container mx-auto py-6 pt-20">
                <TransactionList/>
            </main>
        </div>

    );
}