import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICREHighlights, NewCREHighlights } from '../cre-highlights.model';

export type PartialUpdateCREHighlights = Partial<ICREHighlights> & Pick<ICREHighlights, 'id'>;

export type EntityResponseType = HttpResponse<ICREHighlights>;
export type EntityArrayResponseType = HttpResponse<ICREHighlights[]>;

@Injectable({ providedIn: 'root' })
export class CREHighlightsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cre-highlights');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(cREHighlights: NewCREHighlights): Observable<EntityResponseType> {
    return this.http.post<ICREHighlights>(this.resourceUrl, cREHighlights, { observe: 'response' });
  }

  update(cREHighlights: ICREHighlights): Observable<EntityResponseType> {
    return this.http.put<ICREHighlights>(`${this.resourceUrl}/${this.getCREHighlightsIdentifier(cREHighlights)}`, cREHighlights, {
      observe: 'response',
    });
  }

  partialUpdate(cREHighlights: PartialUpdateCREHighlights): Observable<EntityResponseType> {
    return this.http.patch<ICREHighlights>(`${this.resourceUrl}/${this.getCREHighlightsIdentifier(cREHighlights)}`, cREHighlights, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICREHighlights>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICREHighlights[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCREHighlightsIdentifier(cREHighlights: Pick<ICREHighlights, 'id'>): number {
    return cREHighlights.id;
  }

  compareCREHighlights(o1: Pick<ICREHighlights, 'id'> | null, o2: Pick<ICREHighlights, 'id'> | null): boolean {
    return o1 && o2 ? this.getCREHighlightsIdentifier(o1) === this.getCREHighlightsIdentifier(o2) : o1 === o2;
  }

  addCREHighlightsToCollectionIfMissing<Type extends Pick<ICREHighlights, 'id'>>(
    cREHighlightsCollection: Type[],
    ...cREHighlightsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cREHighlights: Type[] = cREHighlightsToCheck.filter(isPresent);
    if (cREHighlights.length > 0) {
      const cREHighlightsCollectionIdentifiers = cREHighlightsCollection.map(
        cREHighlightsItem => this.getCREHighlightsIdentifier(cREHighlightsItem)!,
      );
      const cREHighlightsToAdd = cREHighlights.filter(cREHighlightsItem => {
        const cREHighlightsIdentifier = this.getCREHighlightsIdentifier(cREHighlightsItem);
        if (cREHighlightsCollectionIdentifiers.includes(cREHighlightsIdentifier)) {
          return false;
        }
        cREHighlightsCollectionIdentifiers.push(cREHighlightsIdentifier);
        return true;
      });
      return [...cREHighlightsToAdd, ...cREHighlightsCollection];
    }
    return cREHighlightsCollection;
  }
}
