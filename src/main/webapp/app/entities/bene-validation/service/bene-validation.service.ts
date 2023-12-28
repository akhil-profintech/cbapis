import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBeneValidation, NewBeneValidation } from '../bene-validation.model';

export type PartialUpdateBeneValidation = Partial<IBeneValidation> & Pick<IBeneValidation, 'id'>;

export type EntityResponseType = HttpResponse<IBeneValidation>;
export type EntityArrayResponseType = HttpResponse<IBeneValidation[]>;

@Injectable({ providedIn: 'root' })
export class BeneValidationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bene-validations');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(beneValidation: NewBeneValidation): Observable<EntityResponseType> {
    return this.http.post<IBeneValidation>(this.resourceUrl, beneValidation, { observe: 'response' });
  }

  update(beneValidation: IBeneValidation): Observable<EntityResponseType> {
    return this.http.put<IBeneValidation>(`${this.resourceUrl}/${this.getBeneValidationIdentifier(beneValidation)}`, beneValidation, {
      observe: 'response',
    });
  }

  partialUpdate(beneValidation: PartialUpdateBeneValidation): Observable<EntityResponseType> {
    return this.http.patch<IBeneValidation>(`${this.resourceUrl}/${this.getBeneValidationIdentifier(beneValidation)}`, beneValidation, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBeneValidation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBeneValidation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBeneValidationIdentifier(beneValidation: Pick<IBeneValidation, 'id'>): number {
    return beneValidation.id;
  }

  compareBeneValidation(o1: Pick<IBeneValidation, 'id'> | null, o2: Pick<IBeneValidation, 'id'> | null): boolean {
    return o1 && o2 ? this.getBeneValidationIdentifier(o1) === this.getBeneValidationIdentifier(o2) : o1 === o2;
  }

  addBeneValidationToCollectionIfMissing<Type extends Pick<IBeneValidation, 'id'>>(
    beneValidationCollection: Type[],
    ...beneValidationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const beneValidations: Type[] = beneValidationsToCheck.filter(isPresent);
    if (beneValidations.length > 0) {
      const beneValidationCollectionIdentifiers = beneValidationCollection.map(
        beneValidationItem => this.getBeneValidationIdentifier(beneValidationItem)!,
      );
      const beneValidationsToAdd = beneValidations.filter(beneValidationItem => {
        const beneValidationIdentifier = this.getBeneValidationIdentifier(beneValidationItem);
        if (beneValidationCollectionIdentifiers.includes(beneValidationIdentifier)) {
          return false;
        }
        beneValidationCollectionIdentifiers.push(beneValidationIdentifier);
        return true;
      });
      return [...beneValidationsToAdd, ...beneValidationCollection];
    }
    return beneValidationCollection;
  }
}
