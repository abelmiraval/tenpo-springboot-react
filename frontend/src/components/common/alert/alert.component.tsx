import React from 'react';

type AlertType = 'success' | 'error' | 'warning' | 'info';

interface AlertProps {
    type: AlertType;
    message: string;
    className?: string;
}

const Alert: React.FC<AlertProps> = ({ type, message, className = '' }) => {
    const alertStyles = {
        success: 'bg-tenpo-primary-light border-tenpo-primary text-tenpo-primary-dark',
        error: 'bg-red-100 border-red-400 text-red-700',
        warning: 'bg-yellow-100 border-yellow-400 text-yellow-700',
        info: 'bg-blue-100 border-blue-400 text-blue-700'
    };

    return (
        <div className={`border px-4 py-3 rounded mb-4 ${alertStyles[type]} ${className}`} role="alert">
            {message}
        </div>
    );
};

export default Alert;