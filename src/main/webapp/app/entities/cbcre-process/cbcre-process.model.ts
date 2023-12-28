import { ICREHighlights } from 'app/entities/cre-highlights/cre-highlights.model';
import { ICREObservations } from 'app/entities/cre-observations/cre-observations.model';
import { IRequestOffer } from 'app/entities/request-offer/request-offer.model';
import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';

export interface ICBCREProcess {
  id: number;
  cbProcessId?: string | null;
  financeRequestId?: string | null;
  anchorTraderId?: string | null;
  anchortTraderGst?: string | null;
  financeAmount?: string | null;
  processDateTime?: string | null;
  creProcessStatus?: string | null;
  responseDateTime?: string | null;
  creRequestId?: string | null;
  cumilativetradescore?: number | null;
  timestamp?: string | null;
  totalAmountRequested?: number | null;
  totalInvoiceAmount?: number | null;
  cREHighlights?: Pick<ICREHighlights, 'id'>[] | null;
  cREObservations?: Pick<ICREObservations, 'id'>[] | null;
  requestOffers?: Pick<IRequestOffer, 'id'>[] | null;
  individualAssessments?: Pick<IIndividualAssessment, 'id'>[] | null;
}

export type NewCBCREProcess = Omit<ICBCREProcess, 'id'> & { id: null };
