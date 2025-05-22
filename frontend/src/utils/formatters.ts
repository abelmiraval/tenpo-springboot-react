/**
 * Formatea un valor numérico como moneda
 * @param value - El valor numérico a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @param currency - El código de moneda ISO (por defecto 'USD')
 * @returns Cadena formateada como moneda con símbolo y decimales
 * @example formatCurrency(1234.56, 'es-PE', 'PEN') // "S/ 1,234.56"
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
 * Formatea una fecha ISO a formato local legible
 * @param dateString - La fecha en formato ISO (YYYY-MM-DD) o cadena de fecha válida
 * @param locale - El código de localización (por defecto 'es-PE')
 * @returns Cadena con la fecha formateada según la localización especificada
 * @example formatDateToLocal('2025-05-22', 'es-PE') // "22/05/2025"
 */
export const formatDateToLocal = (
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
 * Convierte cualquier fecha a formato 'YYYY-MM-DD' para inputs HTML
 * @param inputDate - Fecha en formato ISO, objeto Date, o cadena de fecha
 * @returns Cadena con la fecha en formato 'YYYY-MM-DD' compatible con input[type="date"]
 * @example formatDateForInput('05/22/2025') // "2025-05-22"
 * @example formatDateForInput(new Date()) // "2025-05-22"
 */
export const formatDateForInput = (inputDate: string | Date): string => {
    const date = new Date(inputDate);

    // Validar que la fecha sea válida
    if (isNaN(date.getTime())) {
        console.warn('Invalid date provided, using current date:', inputDate);
        return new Date().toISOString().split('T')[0];
    }

    const year = date.getUTCFullYear();
    const month = String(date.getUTCMonth() + 1).padStart(2, '0');
    const day = String(date.getUTCDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
};

/**
 * Convierte un valor decimal a formato de porcentaje
 * @param value - El valor decimal a formatear (ej: 0.25 para 25%)
 * @param locale - El código de localización (por defecto 'en-US')
 * @param minimumFractionDigits - Mínimo de decimales (por defecto 1)
 * @param maximumFractionDigits - Máximo de decimales (por defecto 2)
 * @returns Cadena formateada como porcentaje con símbolo %
 * @example formatPercentage(0.1567) // "15.7%"
 */
export const formatPercentage = (
    value: number,
    locale: string = 'en-US',
    minimumFractionDigits: number = 1,
    maximumFractionDigits: number = 2
): string => {
    return new Intl.NumberFormat(locale, {
        style: 'percent',
        minimumFractionDigits,
        maximumFractionDigits
    }).format(value);
};

/**
 * Formatea un número con separadores de miles según la localización
 * @param value - El valor numérico a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @param minimumFractionDigits - Mínimo de decimales a mostrar (por defecto 0)
 * @param maximumFractionDigits - Máximo de decimales a mostrar (por defecto 2)
 * @returns Cadena formateada con separadores de miles
 * @example formatNumber(1234567.89, 'es-PE') // "1,234,567.89"
 */
export const formatNumber = (
    value: number,
    locale: string = 'en-US',
    minimumFractionDigits: number = 0,
    maximumFractionDigits: number = 2
): string => {
    return new Intl.NumberFormat(locale, {
        minimumFractionDigits,
        maximumFractionDigits
    }).format(value);
};

/**
 * Obtiene la fecha actual en formato 'YYYY-MM-DD'
 * @returns Cadena con la fecha actual en formato compatible con input[type="date"]
 * @example getCurrentDateForInput() // "2025-05-22"
 */
export const getCurrentDateForInput = (): string => {
    return new Date().toISOString().split('T')[0];
};

/**
 * Valida si una cadena de fecha está en formato 'YYYY-MM-DD'
 * @param date - La cadena de fecha a validar
 * @returns true si el formato es válido, false en caso contrario
 * @example isValidInputDateFormat('2025-05-22') // true
 * @example isValidInputDateFormat('05/22/2025') // false
 */
export const isValidInputDateFormat = (date: string): boolean => {
    const regex = /^\d{4}-\d{2}-\d{2}$/;
    if (!regex.test(date)) return false;

    // Validar que sea una fecha real
    const dateObj = new Date(date + 'T00:00:00Z');
    return !isNaN(dateObj.getTime()) && date === dateObj.toISOString().split('T')[0];
};

/**
 * Convierte una fecha a formato ISO string para APIs
 * @param inputDate - Fecha en cualquier formato válido
 * @returns Cadena en formato ISO (YYYY-MM-DDTHH:mm:ss.sssZ) o null si es inválida
 * @example formatDateForAPI('2025-05-22') // "2025-05-22T00:00:00.000Z"
 */
export const formatDateForAPI = (inputDate: string | Date | null | undefined): string | null => {
    if (!inputDate) return null;

    try {
        const date = new Date(inputDate);
        if (isNaN(date.getTime())) return null;

        return date.toISOString();
    } catch (error) {
        console.error('Error formatting date for API:', error);
        return null;
    }
};

/**
 * Formatea un número como moneda compacta (K, M, B)
 * @param value - El valor numérico a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @param currency - El código de moneda ISO (por defecto 'USD')
 * @returns Cadena formateada como moneda compacta
 * @example formatCompactCurrency(1234567, 'en-US', 'USD') // "$1.2M"
 */
export const formatCompactCurrency = (
    value: number,
    locale: string = 'en-US',
    currency: string = 'USD'
): string => {
    return new Intl.NumberFormat(locale, {
        style: 'currency',
        currency: currency,
        notation: 'compact',
        maximumFractionDigits: 1
    }).format(value);
};

/**
 * Formatea un número en notación compacta (K, M, B)
 * @param value - El valor numérico a formatear
 * @param locale - El código de localización (por defecto 'en-US')
 * @returns Cadena formateada en notación compacta
 * @example formatCompactNumber(1234567) // "1.2M"
 */
export const formatCompactNumber = (
    value: number,
    locale: string = 'en-US'
): string => {
    return new Intl.NumberFormat(locale, {
        notation: 'compact',
        maximumFractionDigits: 1
    }).format(value);
};