import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IIndividualAssessment, NewIndividualAssessment } from '../individual-assessment.model';

export type PartialUpdateIndividualAssessment = Partial<IIndividualAssessment> & Pick<IIndividualAssessment, 'id'>;

export type EntityResponseType = HttpResponse<IIndividualAssessment>;
export type EntityArrayResponseType = HttpResponse<IIndividualAssessment[]>;

@Injectable({ providedIn: 'root' })
export class IndividualAssessmentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/individual-assessments');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(individualAssessment: NewIndividualAssessment): Observable<EntityResponseType> {
    return this.http.post<IIndividualAssessment>(this.resourceUrl, individualAssessment, { observe: 'response' });
  }

  update(individualAssessment: IIndividualAssessment): Observable<EntityResponseType> {
    return this.http.put<IIndividualAssessment>(
      `${this.resourceUrl}/${this.getIndividualAssessmentIdentifier(individualAssessment)}`,
      individualAssessment,
      { observe: 'response' },
    );
  }

  partialUpdate(individualAssessment: PartialUpdateIndividualAssessment): Observable<EntityResponseType> {
    return this.http.patch<IIndividualAssessment>(
      `${this.resourceUrl}/${this.getIndividualAssessmentIdentifier(individualAssessment)}`,
      individualAssessment,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIndividualAssessment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIndividualAssessment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getIndividualAssessmentIdentifier(individualAssessment: Pick<IIndividualAssessment, 'id'>): number {
    return individualAssessment.id;
  }

  compareIndividualAssessment(o1: Pick<IIndividualAssessment, 'id'> | null, o2: Pick<IIndividualAssessment, 'id'> | null): boolean {
    return o1 && o2 ? this.getIndividualAssessmentIdentifier(o1) === this.getIndividualAssessmentIdentifier(o2) : o1 === o2;
  }

  addIndividualAssessmentToCollectionIfMissing<Type extends Pick<IIndividualAssessment, 'id'>>(
    individualAssessmentCollection: Type[],
    ...individualAssessmentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const individualAssessments: Type[] = individualAssessmentsToCheck.filter(isPresent);
    if (individualAssessments.length > 0) {
      const individualAssessmentCollectionIdentifiers = individualAssessmentCollection.map(
        individualAssessmentItem => this.getIndividualAssessmentIdentifier(individualAssessmentItem)!,
      );
      const individualAssessmentsToAdd = individualAssessments.filter(individualAssessmentItem => {
        const individualAssessmentIdentifier = this.getIndividualAssessmentIdentifier(individualAssessmentItem);
        if (individualAssessmentCollectionIdentifiers.includes(individualAssessmentIdentifier)) {
          return false;
        }
        individualAssessmentCollectionIdentifiers.push(individualAssessmentIdentifier);
        return true;
      });
      return [...individualAssessmentsToAdd, ...individualAssessmentCollection];
    }
    return individualAssessmentCollection;
  }
}
