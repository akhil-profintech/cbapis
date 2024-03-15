import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';

export interface ICREHighlights {
  id: number;
  creHighlightsId?: number | null;
  creHighlightsUlidId?: string | null;
  creRequestId?: string | null;
  highlights?: string | null;
  assessmentId?: number | null;
  individualassessment?: Pick<IIndividualAssessment, 'id' | 'assessmentId'> | null;
}

export type NewCREHighlights = Omit<ICREHighlights, 'id'> & { id: null };
