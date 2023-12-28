import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';

export interface ICREObservations {
  id: number;
  creObservationsId?: number | null;
  creRequestId?: string | null;
  observations?: string | null;
  cbcreprocess?: Pick<ICBCREProcess, 'id' | 'cbProcessId'> | null;
  individualassessment?: Pick<IIndividualAssessment, 'id' | 'assessmentId'> | null;
}

export type NewCREObservations = Omit<ICREObservations, 'id'> & { id: null };
