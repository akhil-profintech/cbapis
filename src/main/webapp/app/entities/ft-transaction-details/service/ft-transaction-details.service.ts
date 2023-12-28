import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFTTransactionDetails, NewFTTransactionDetails } from '../ft-transaction-details.model';

export type PartialUpdateFTTransactionDetails = Partial<IFTTransactionDetails> & Pick<IFTTransactionDetails, 'id'>;

export type EntityResponseType = HttpResponse<IFTTransactionDetails>;
export type EntityArrayResponseType = HttpResponse<IFTTransactionDetails[]>;

@Injectable({ providedIn: 'root' })
export class FTTransactionDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ft-transaction-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(fTTransactionDetails: NewFTTransactionDetails): Observable<EntityResponseType> {
    return this.http.post<IFTTransactionDetails>(this.resourceUrl, fTTransactionDetails, { observe: 'response' });
  }

  update(fTTransactionDetails: IFTTransactionDetails): Observable<EntityResponseType> {
    return this.http.put<IFTTransactionDetails>(
      `${this.resourceUrl}/${this.getFTTransactionDetailsIdentifier(fTTransactionDetails)}`,
      fTTransactionDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(fTTransactionDetails: PartialUpdateFTTransactionDetails): Observable<EntityResponseType> {
    return this.http.patch<IFTTransactionDetails>(
      `${this.resourceUrl}/${this.getFTTransactionDetailsIdentifier(fTTransactionDetails)}`,
      fTTransactionDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFTTransactionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFTTransactionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFTTransactionDetailsIdentifier(fTTransactionDetails: Pick<IFTTransactionDetails, 'id'>): number {
    return fTTransactionDetails.id;
  }

  compareFTTransactionDetails(o1: Pick<IFTTransactionDetails, 'id'> | null, o2: Pick<IFTTransactionDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getFTTransactionDetailsIdentifier(o1) === this.getFTTransactionDetailsIdentifier(o2) : o1 === o2;
  }

  addFTTransactionDetailsToCollectionIfMissing<Type extends Pick<IFTTransactionDetails, 'id'>>(
    fTTransactionDetailsCollection: Type[],
    ...fTTransactionDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fTTransactionDetails: Type[] = fTTransactionDetailsToCheck.filter(isPresent);
    if (fTTransactionDetails.length > 0) {
      const fTTransactionDetailsCollectionIdentifiers = fTTransactionDetailsCollection.map(
        fTTransactionDetailsItem => this.getFTTransactionDetailsIdentifier(fTTransactionDetailsItem)!,
      );
      const fTTransactionDetailsToAdd = fTTransactionDetails.filter(fTTransactionDetailsItem => {
        const fTTransactionDetailsIdentifier = this.getFTTransactionDetailsIdentifier(fTTransactionDetailsItem);
        if (fTTransactionDetailsCollectionIdentifiers.includes(fTTransactionDetailsIdentifier)) {
          return false;
        }
        fTTransactionDetailsCollectionIdentifiers.push(fTTransactionDetailsIdentifier);
        return true;
      });
      return [...fTTransactionDetailsToAdd, ...fTTransactionDetailsCollection];
    }
    return fTTransactionDetailsCollection;
  }
}
