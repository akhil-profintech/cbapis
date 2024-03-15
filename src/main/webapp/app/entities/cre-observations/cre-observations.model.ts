import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';

export interface ICREObservations {
  id: number;
  creObservationsId?: number | null;
  creObservationsUlidId?: string | null;
  creRequestId?: string | null;
  observations?: string | null;
  assessmentId?: number | null;
  individualassessment?: Pick<IIndividualAssessment, 'id' | 'assessmentId'> | null;
}

export type NewCREObservations = Omit<ICREObservations, 'id'> & { id: null };
