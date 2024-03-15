import { ICREHighlights } from 'app/entities/cre-highlights/cre-highlights.model';
import { ICREObservations } from 'app/entities/cre-observations/cre-observations.model';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';

export interface IIndividualAssessment {
  id: number;
  assessmentId?: number | null;
  assessmentUlidId?: string | null;
  creditScore?: number | null;
  finalVerdict?: string | null;
  creRequestId?: string | null;
  timestamp?: string | null;
  tradePartnerGST?: string | null;
  tradePartnerId?: string | null;
  invoiceAmount?: number | null;
  invoiceId?: string | null;
  baseScore?: string | null;
  ctin?: string | null;
  invDate?: string | null;
  cbProcessId?: number | null;
  grnPresent?: boolean | null;
  einvoicePresent?: boolean | null;
  ewayBillPresent?: boolean | null;
  tradePartnerConfirmation?: boolean | null;
  cREHighlights?: Pick<ICREHighlights, 'id'>[] | null;
  cREObservations?: Pick<ICREObservations, 'id'>[] | null;
  cbcreprocess?: Pick<ICBCREProcess, 'id' | 'cbProcessId'> | null;
}

export type NewIndividualAssessment = Omit<IIndividualAssessment, 'id'> & { id: null };
