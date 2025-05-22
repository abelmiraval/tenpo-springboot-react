import type {ChangeEvent} from "react";

interface InputProps {
    label: string;
    name: string;
    value: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
    error?: string;
    placeholder?: string;
    type?: string;
    className?: string;
}

const Input: React.FC<InputProps> = (
    {
        label,
        name,
        value,
        onChange,
        error,
        placeholder = '',
        type = 'text',
        className = ''
    }) => {
    return (
        <div className={className}>
            <label className="block text-tenpo-neutral-600 mb-2">{label}</label>
            <input
                type={type}
                name={name}
                value={value}
                onChange={onChange}
                className={`w-full p-2 border rounded ${
                    error
                        ? 'border-tenpo-error'
                        : 'border-tenpo-neutral-300 focus:border-tenpo-primary focus:ring-1 focus:ring-tenpo-primary'
                }`}
                placeholder={placeholder}
            />
            {error && <p className="text-tenpo-error text-sm mt-1">{error}</p>}
        </div>
    );
};

export default Input;