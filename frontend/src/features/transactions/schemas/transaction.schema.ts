import { z } from 'zod';

export const transactionSchema = z.object({
  amount: z
      .number({ required_error: "Amount is required" })
      .min(0.01, "Amount must be greater than 0"),
  category: z
      .string({ required_error: "Category is required" })
      .min(1, "Category is required"),
  username: z
      .string({ required_error: "Username is required" })
      .min(1, "Username is required"),
  date: z
      .string({ required_error: "Date is required" })
      .min(1, "Date is required"),
  description: z
      .string()
      .optional()
});

export const updateTransactionSchema = transactionSchema.extend({
  id: z.number()
});

export type TransactionFormData = z.infer<typeof transactionSchema>;
export type UpdateTransactionFormData = z.infer<typeof updateTransactionSchema>;

export const TRANSACTION_CATEGORIES = [
  { value: 'salary', label: 'Salary' },
  { value: 'food', label: 'Food' },
  { value: 'transport', label: 'Transport' },
  { value: 'education', label: 'Education' },
  { value: 'health', label: 'Health' },
  { value: 'other', label: 'Other' }
];
