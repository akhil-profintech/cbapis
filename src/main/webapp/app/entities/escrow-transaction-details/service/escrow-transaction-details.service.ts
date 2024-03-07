import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEscrowTransactionDetails, NewEscrowTransactionDetails } from '../escrow-transaction-details.model';

export type PartialUpdateEscrowTransactionDetails = Partial<IEscrowTransactionDetails> & Pick<IEscrowTransactionDetails, 'id'>;

export type EntityResponseType = HttpResponse<IEscrowTransactionDetails>;
export type EntityArrayResponseType = HttpResponse<IEscrowTransactionDetails[]>;

@Injectable({ providedIn: 'root' })
export class EscrowTransactionDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/escrow-transaction-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(escrowTransactionDetails: NewEscrowTransactionDetails): Observable<EntityResponseType> {
    return this.http.post<IEscrowTransactionDetails>(this.resourceUrl, escrowTransactionDetails, { observe: 'response' });
  }

  update(escrowTransactionDetails: IEscrowTransactionDetails): Observable<EntityResponseType> {
    return this.http.put<IEscrowTransactionDetails>(
      `${this.resourceUrl}/${this.getEscrowTransactionDetailsIdentifier(escrowTransactionDetails)}`,
      escrowTransactionDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(escrowTransactionDetails: PartialUpdateEscrowTransactionDetails): Observable<EntityResponseType> {
    return this.http.patch<IEscrowTransactionDetails>(
      `${this.resourceUrl}/${this.getEscrowTransactionDetailsIdentifier(escrowTransactionDetails)}`,
      escrowTransactionDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEscrowTransactionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEscrowTransactionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEscrowTransactionDetailsIdentifier(escrowTransactionDetails: Pick<IEscrowTransactionDetails, 'id'>): number {
    return escrowTransactionDetails.id;
  }

  compareEscrowTransactionDetails(
    o1: Pick<IEscrowTransactionDetails, 'id'> | null,
    o2: Pick<IEscrowTransactionDetails, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getEscrowTransactionDetailsIdentifier(o1) === this.getEscrowTransactionDetailsIdentifier(o2) : o1 === o2;
  }

  addEscrowTransactionDetailsToCollectionIfMissing<Type extends Pick<IEscrowTransactionDetails, 'id'>>(
    escrowTransactionDetailsCollection: Type[],
    ...escrowTransactionDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const escrowTransactionDetails: Type[] = escrowTransactionDetailsToCheck.filter(isPresent);
    if (escrowTransactionDetails.length > 0) {
      const escrowTransactionDetailsCollectionIdentifiers = escrowTransactionDetailsCollection.map(
        escrowTransactionDetailsItem => this.getEscrowTransactionDetailsIdentifier(escrowTransactionDetailsItem)!,
      );
      const escrowTransactionDetailsToAdd = escrowTransactionDetails.filter(escrowTransactionDetailsItem => {
        const escrowTransactionDetailsIdentifier = this.getEscrowTransactionDetailsIdentifier(escrowTransactionDetailsItem);
        if (escrowTransactionDetailsCollectionIdentifiers.includes(escrowTransactionDetailsIdentifier)) {
          return false;
        }
        escrowTransactionDetailsCollectionIdentifiers.push(escrowTransactionDetailsIdentifier);
        return true;
      });
      return [...escrowTransactionDetailsToAdd, ...escrowTransactionDetailsCollection];
    }
    return escrowTransactionDetailsCollection;
  }
}
