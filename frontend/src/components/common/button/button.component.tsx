import type {ButtonHTMLAttributes, ReactNode} from "react";

type ButtonVariant = 'primary' | 'secondary' | 'outline' | 'danger' | 'ghost';
type ButtonSize = 'sm' | 'md' | 'lg';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    children: ReactNode;
    type?: 'button' | 'submit' | 'reset';
    variant?: ButtonVariant;
    size?: ButtonSize;
    fullWidth?: boolean;
    disabled?: boolean;
    className?: string;
}

export default function Button(
    {
        children,
        type = 'button',
        variant = 'primary',
        size = 'md',
        fullWidth = false,
        disabled = false,
        className = '',
        onClick,
        ...props
    }: ButtonProps) {
    const baseClasses =
        'font-medium transition duration-300 rounded focus:outline-none focus:ring-2';

    const variantClasses: Record<ButtonVariant, string> = {
        primary:
            'bg-tenpo-primary text-tenpo-neutral-50 hover:bg-tenpo-primary-dark focus:ring-tenpo-primary-light',
        secondary:
            'bg-tenpo-secondary text-tenpo-neutral-50 hover:bg-tenpo-secondary-dark focus:ring-tenpo-secondary-light',
        outline:
            'bg-transparent border border-tenpo-primary text-tenpo-primary hover:bg-tenpo-primary hover:text-tenpo-neutral-50 focus:ring-tenpo-primary-light',
        danger:
            'bg-tenpo-error text-tenpo-neutral-50 hover:bg-red-700 focus:ring-tenpo-error',
        ghost:
            'bg-transparent text-tenpo-neutral-800 hover:bg-tenpo-neutral-200 focus:ring-tenpo-neutral-300',
    };

    const sizeClasses: Record<ButtonSize, string> = {
        sm: 'py-1 px-2 text-sm',
        md: 'py-2 px-4 text-base',
        lg: 'py-3 px-6 text-lg',
    };

    const classes = [
        baseClasses,
        variantClasses[variant] || variantClasses.primary,
        sizeClasses[size] || sizeClasses.md,
        fullWidth ? 'w-full' : '',
        disabled ? 'opacity-60 cursor-not-allowed' : 'cursor-pointer',
        className,
    ].join(' ');

    return (
        <button
            type={type}
            className={classes}
            disabled={disabled}
            onClick={onClick}
            {...props}
        >
            {children}
        </button>
    );
}
