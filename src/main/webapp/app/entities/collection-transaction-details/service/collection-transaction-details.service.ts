import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICollectionTransactionDetails, NewCollectionTransactionDetails } from '../collection-transaction-details.model';

export type PartialUpdateCollectionTransactionDetails = Partial<ICollectionTransactionDetails> & Pick<ICollectionTransactionDetails, 'id'>;

export type EntityResponseType = HttpResponse<ICollectionTransactionDetails>;
export type EntityArrayResponseType = HttpResponse<ICollectionTransactionDetails[]>;

@Injectable({ providedIn: 'root' })
export class CollectionTransactionDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/collection-transaction-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(collectionTransactionDetails: NewCollectionTransactionDetails): Observable<EntityResponseType> {
    return this.http.post<ICollectionTransactionDetails>(this.resourceUrl, collectionTransactionDetails, { observe: 'response' });
  }

  update(collectionTransactionDetails: ICollectionTransactionDetails): Observable<EntityResponseType> {
    return this.http.put<ICollectionTransactionDetails>(
      `${this.resourceUrl}/${this.getCollectionTransactionDetailsIdentifier(collectionTransactionDetails)}`,
      collectionTransactionDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(collectionTransactionDetails: PartialUpdateCollectionTransactionDetails): Observable<EntityResponseType> {
    return this.http.patch<ICollectionTransactionDetails>(
      `${this.resourceUrl}/${this.getCollectionTransactionDetailsIdentifier(collectionTransactionDetails)}`,
      collectionTransactionDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICollectionTransactionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICollectionTransactionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCollectionTransactionDetailsIdentifier(collectionTransactionDetails: Pick<ICollectionTransactionDetails, 'id'>): number {
    return collectionTransactionDetails.id;
  }

  compareCollectionTransactionDetails(
    o1: Pick<ICollectionTransactionDetails, 'id'> | null,
    o2: Pick<ICollectionTransactionDetails, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getCollectionTransactionDetailsIdentifier(o1) === this.getCollectionTransactionDetailsIdentifier(o2) : o1 === o2;
  }

  addCollectionTransactionDetailsToCollectionIfMissing<Type extends Pick<ICollectionTransactionDetails, 'id'>>(
    collectionTransactionDetailsCollection: Type[],
    ...collectionTransactionDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const collectionTransactionDetails: Type[] = collectionTransactionDetailsToCheck.filter(isPresent);
    if (collectionTransactionDetails.length > 0) {
      const collectionTransactionDetailsCollectionIdentifiers = collectionTransactionDetailsCollection.map(
        collectionTransactionDetailsItem => this.getCollectionTransactionDetailsIdentifier(collectionTransactionDetailsItem)!,
      );
      const collectionTransactionDetailsToAdd = collectionTransactionDetails.filter(collectionTransactionDetailsItem => {
        const collectionTransactionDetailsIdentifier = this.getCollectionTransactionDetailsIdentifier(collectionTransactionDetailsItem);
        if (collectionTransactionDetailsCollectionIdentifiers.includes(collectionTransactionDetailsIdentifier)) {
          return false;
        }
        collectionTransactionDetailsCollectionIdentifiers.push(collectionTransactionDetailsIdentifier);
        return true;
      });
      return [...collectionTransactionDetailsToAdd, ...collectionTransactionDetailsCollection];
    }
    return collectionTransactionDetailsCollection;
  }
}
