import type {ReactNode} from "react";

interface CardProps {
    children: ReactNode;
    className?: string;
}

const Card: React.FC<CardProps> = ({ children, className = '' }) => {
    return (
        <div className={`bg-tenpo-neutral-50 p-6 rounded-lg shadow-md ${className}`}>
            {children}
        </div>
    );
};

export default Card;