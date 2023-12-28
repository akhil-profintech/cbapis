import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';

export interface ICREHighlights {
  id: number;
  creHighlightsId?: number | null;
  creRequestId?: string | null;
  highlights?: string | null;
  cbcreprocess?: Pick<ICBCREProcess, 'id' | 'cbProcessId'> | null;
  individualassessment?: Pick<IIndividualAssessment, 'id' | 'assessmentId'> | null;
}

export type NewCREHighlights = Omit<ICREHighlights, 'id'> & { id: null };
