import React from 'react';

type Option = {
    label: string;
    value: string | number;
};

type SelectProps = {
    label?: string;
    name: string;
    value: string | number;
    onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
    options?: Option[];
    placeholder?: string;
    error?: string;
    className?: string;
    [key: string]: any;
};

const Select: React.FC<SelectProps> = (
    {
        label,
        name,
        value,
        onChange,
        options = [],
        placeholder = "Seleccione una opción",
        error,
        className = '',
        ...props
    }) => {
    const selectClasses = `w-full p-2 border rounded ${
        error
            ? 'border-tenpo-error focus:border-tenpo-error focus:ring-1 focus:ring-tenpo-error'
            : 'border-tenpo-neutral-300 focus:border-tenpo-primary focus:ring-1 focus:ring-tenpo-primary'
    }`;

    return (
        <div className={className}>
            {label && (
                <label className="block text-tenpo-neutral-600 mb-2" htmlFor={name}>
                    {label}
                </label>
            )}
            <select
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                className={selectClasses}
                {...props}
            >
                <option value="">{placeholder}</option>
                {options.map((option) => (
                    <option key={option.value} value={option.value}>
                        {option.label}
                    </option>
                ))}
            </select>
            {error && <p className="text-tenpo-error text-sm mt-1">{error}</p>}
        </div>
    );
};

export default Select;
