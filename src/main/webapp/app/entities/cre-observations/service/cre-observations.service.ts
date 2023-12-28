import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICREObservations, NewCREObservations } from '../cre-observations.model';

export type PartialUpdateCREObservations = Partial<ICREObservations> & Pick<ICREObservations, 'id'>;

export type EntityResponseType = HttpResponse<ICREObservations>;
export type EntityArrayResponseType = HttpResponse<ICREObservations[]>;

@Injectable({ providedIn: 'root' })
export class CREObservationsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cre-observations');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(cREObservations: NewCREObservations): Observable<EntityResponseType> {
    return this.http.post<ICREObservations>(this.resourceUrl, cREObservations, { observe: 'response' });
  }

  update(cREObservations: ICREObservations): Observable<EntityResponseType> {
    return this.http.put<ICREObservations>(`${this.resourceUrl}/${this.getCREObservationsIdentifier(cREObservations)}`, cREObservations, {
      observe: 'response',
    });
  }

  partialUpdate(cREObservations: PartialUpdateCREObservations): Observable<EntityResponseType> {
    return this.http.patch<ICREObservations>(`${this.resourceUrl}/${this.getCREObservationsIdentifier(cREObservations)}`, cREObservations, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICREObservations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICREObservations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCREObservationsIdentifier(cREObservations: Pick<ICREObservations, 'id'>): number {
    return cREObservations.id;
  }

  compareCREObservations(o1: Pick<ICREObservations, 'id'> | null, o2: Pick<ICREObservations, 'id'> | null): boolean {
    return o1 && o2 ? this.getCREObservationsIdentifier(o1) === this.getCREObservationsIdentifier(o2) : o1 === o2;
  }

  addCREObservationsToCollectionIfMissing<Type extends Pick<ICREObservations, 'id'>>(
    cREObservationsCollection: Type[],
    ...cREObservationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cREObservations: Type[] = cREObservationsToCheck.filter(isPresent);
    if (cREObservations.length > 0) {
      const cREObservationsCollectionIdentifiers = cREObservationsCollection.map(
        cREObservationsItem => this.getCREObservationsIdentifier(cREObservationsItem)!,
      );
      const cREObservationsToAdd = cREObservations.filter(cREObservationsItem => {
        const cREObservationsIdentifier = this.getCREObservationsIdentifier(cREObservationsItem);
        if (cREObservationsCollectionIdentifiers.includes(cREObservationsIdentifier)) {
          return false;
        }
        cREObservationsCollectionIdentifiers.push(cREObservationsIdentifier);
        return true;
      });
      return [...cREObservationsToAdd, ...cREObservationsCollection];
    }
    return cREObservationsCollection;
  }
}
