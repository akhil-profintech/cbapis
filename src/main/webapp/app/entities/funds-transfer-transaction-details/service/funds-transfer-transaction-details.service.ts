import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFundsTransferTransactionDetails, NewFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';

export type PartialUpdateFundsTransferTransactionDetails = Partial<IFundsTransferTransactionDetails> &
  Pick<IFundsTransferTransactionDetails, 'id'>;

export type EntityResponseType = HttpResponse<IFundsTransferTransactionDetails>;
export type EntityArrayResponseType = HttpResponse<IFundsTransferTransactionDetails[]>;

@Injectable({ providedIn: 'root' })
export class FundsTransferTransactionDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/funds-transfer-transaction-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(fundsTransferTransactionDetails: NewFundsTransferTransactionDetails): Observable<EntityResponseType> {
    return this.http.post<IFundsTransferTransactionDetails>(this.resourceUrl, fundsTransferTransactionDetails, { observe: 'response' });
  }

  update(fundsTransferTransactionDetails: IFundsTransferTransactionDetails): Observable<EntityResponseType> {
    return this.http.put<IFundsTransferTransactionDetails>(
      `${this.resourceUrl}/${this.getFundsTransferTransactionDetailsIdentifier(fundsTransferTransactionDetails)}`,
      fundsTransferTransactionDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(fundsTransferTransactionDetails: PartialUpdateFundsTransferTransactionDetails): Observable<EntityResponseType> {
    return this.http.patch<IFundsTransferTransactionDetails>(
      `${this.resourceUrl}/${this.getFundsTransferTransactionDetailsIdentifier(fundsTransferTransactionDetails)}`,
      fundsTransferTransactionDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFundsTransferTransactionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFundsTransferTransactionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFundsTransferTransactionDetailsIdentifier(fundsTransferTransactionDetails: Pick<IFundsTransferTransactionDetails, 'id'>): number {
    return fundsTransferTransactionDetails.id;
  }

  compareFundsTransferTransactionDetails(
    o1: Pick<IFundsTransferTransactionDetails, 'id'> | null,
    o2: Pick<IFundsTransferTransactionDetails, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getFundsTransferTransactionDetailsIdentifier(o1) === this.getFundsTransferTransactionDetailsIdentifier(o2)
      : o1 === o2;
  }

  addFundsTransferTransactionDetailsToCollectionIfMissing<Type extends Pick<IFundsTransferTransactionDetails, 'id'>>(
    fundsTransferTransactionDetailsCollection: Type[],
    ...fundsTransferTransactionDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fundsTransferTransactionDetails: Type[] = fundsTransferTransactionDetailsToCheck.filter(isPresent);
    if (fundsTransferTransactionDetails.length > 0) {
      const fundsTransferTransactionDetailsCollectionIdentifiers = fundsTransferTransactionDetailsCollection.map(
        fundsTransferTransactionDetailsItem => this.getFundsTransferTransactionDetailsIdentifier(fundsTransferTransactionDetailsItem)!,
      );
      const fundsTransferTransactionDetailsToAdd = fundsTransferTransactionDetails.filter(fundsTransferTransactionDetailsItem => {
        const fundsTransferTransactionDetailsIdentifier =
          this.getFundsTransferTransactionDetailsIdentifier(fundsTransferTransactionDetailsItem);
        if (fundsTransferTransactionDetailsCollectionIdentifiers.includes(fundsTransferTransactionDetailsIdentifier)) {
          return false;
        }
        fundsTransferTransactionDetailsCollectionIdentifiers.push(fundsTransferTransactionDetailsIdentifier);
        return true;
      });
      return [...fundsTransferTransactionDetailsToAdd, ...fundsTransferTransactionDetailsCollection];
    }
    return fundsTransferTransactionDetailsCollection;
  }
}
