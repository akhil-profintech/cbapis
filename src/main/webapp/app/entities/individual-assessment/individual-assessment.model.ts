import { ICREHighlights } from 'app/entities/cre-highlights/cre-highlights.model';
import { ICREObservations } from 'app/entities/cre-observations/cre-observations.model';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';

export interface IIndividualAssessment {
  id: number;
  assessmentId?: string | null;
  creditScore?: number | null;
  finalverdict?: string | null;
  creRequestId?: string | null;
  timestamp?: string | null;
  tradepartnerGST?: string | null;
  tradepartnerId?: string | null;
  invoiceAmount?: number | null;
  invoiceId?: string | null;
  cREHighlights?: Pick<ICREHighlights, 'id'>[] | null;
  cREObservations?: Pick<ICREObservations, 'id'>[] | null;
  cbcreprocess?: Pick<ICBCREProcess, 'id' | 'cbProcessId'> | null;
}

export type NewIndividualAssessment = Omit<IIndividualAssessment, 'id'> & { id: null };
