/**
 * Formatea un valor numérico como moneda
 * @param value - El valor a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @param currency - El código de moneda (por defecto 'USD')
 * @returns String formateado como moneda
 */
export const formatCurrency = (
    value: number,
    locale: string = 'en-US',
    currency: string = 'USD'
): string => {
    return new Intl.NumberFormat(locale, {
        style: 'currency',
        currency: currency,
        minimumFractionDigits: 2
    }).format(value);
};

/**
 * Formatea una fecha en formato ISO a formato local
 * @param dateString - La fecha en formato ISO (YYYY-MM-DD)
 * @param locale - El código de localización (por defecto 'en-US')
 * @returns String con la fecha formateada
 */
export const formatDate = (
    dateString: string,
    locale: string = 'es-PE'
): string => {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat(locale, {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        timeZone: 'UTC'
    }).format(date);
};

/**
 * Convierte un número a formato de porcentaje
 * @param value - El valor a formatear como porcentaje
 * @param locale - El código de localización (por defecto 'en-US')
 * @returns String formateado como porcentaje
 */
export const formatPercentage = (
    value: number,
    locale: string = 'en-US'
): string => {
    return new Intl.NumberFormat(locale, {
        style: 'percent',
        minimumFractionDigits: 1,
        maximumFractionDigits: 2
    }).format(value / 100);
};

/**
 * Formatea un número con separadores de miles
 * @param value - El valor a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @returns String formateado con separadores de miles
 */
export const formatNumber = (
    value: number,
    locale: string = 'en-US'
): string => {
    return new Intl.NumberFormat(locale).format(value);
};