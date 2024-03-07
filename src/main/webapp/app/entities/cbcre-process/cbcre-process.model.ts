import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface ICBCREProcess {
  id: number;
  cbProcessId?: number | null;
  cbProcessUlidId?: string | null;
  cbProcessRefNo?: string | null;
  anchorTraderId?: string | null;
  anchorTraderGst?: string | null;
  financeAmount?: string | null;
  processDateTime?: string | null;
  creProcessStatus?: string | null;
  responseDateTime?: string | null;
  creRequestId?: string | null;
  cumilativeTradeScore?: number | null;
  timestamp?: string | null;
  totalAmountRequested?: number | null;
  totalInvoiceAmount?: number | null;
  individualAssessments?: Pick<IIndividualAssessment, 'id'>[] | null;
  financeRequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
}

export type NewCBCREProcess = Omit<ICBCREProcess, 'id'> & { id: null };
