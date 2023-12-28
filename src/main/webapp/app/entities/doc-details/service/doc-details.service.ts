import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDocDetails, NewDocDetails } from '../doc-details.model';

export type PartialUpdateDocDetails = Partial<IDocDetails> & Pick<IDocDetails, 'id'>;

export type EntityResponseType = HttpResponse<IDocDetails>;
export type EntityArrayResponseType = HttpResponse<IDocDetails[]>;

@Injectable({ providedIn: 'root' })
export class DocDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/doc-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(docDetails: NewDocDetails): Observable<EntityResponseType> {
    return this.http.post<IDocDetails>(this.resourceUrl, docDetails, { observe: 'response' });
  }

  update(docDetails: IDocDetails): Observable<EntityResponseType> {
    return this.http.put<IDocDetails>(`${this.resourceUrl}/${this.getDocDetailsIdentifier(docDetails)}`, docDetails, {
      observe: 'response',
    });
  }

  partialUpdate(docDetails: PartialUpdateDocDetails): Observable<EntityResponseType> {
    return this.http.patch<IDocDetails>(`${this.resourceUrl}/${this.getDocDetailsIdentifier(docDetails)}`, docDetails, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDocDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDocDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDocDetailsIdentifier(docDetails: Pick<IDocDetails, 'id'>): number {
    return docDetails.id;
  }

  compareDocDetails(o1: Pick<IDocDetails, 'id'> | null, o2: Pick<IDocDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getDocDetailsIdentifier(o1) === this.getDocDetailsIdentifier(o2) : o1 === o2;
  }

  addDocDetailsToCollectionIfMissing<Type extends Pick<IDocDetails, 'id'>>(
    docDetailsCollection: Type[],
    ...docDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const docDetails: Type[] = docDetailsToCheck.filter(isPresent);
    if (docDetails.length > 0) {
      const docDetailsCollectionIdentifiers = docDetailsCollection.map(docDetailsItem => this.getDocDetailsIdentifier(docDetailsItem)!);
      const docDetailsToAdd = docDetails.filter(docDetailsItem => {
        const docDetailsIdentifier = this.getDocDetailsIdentifier(docDetailsItem);
        if (docDetailsCollectionIdentifiers.includes(docDetailsIdentifier)) {
          return false;
        }
        docDetailsCollectionIdentifiers.push(docDetailsIdentifier);
        return true;
      });
      return [...docDetailsToAdd, ...docDetailsCollection];
    }
    return docDetailsCollection;
  }
}
