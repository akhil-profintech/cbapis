import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreditAccountDetails, NewCreditAccountDetails } from '../credit-account-details.model';

export type PartialUpdateCreditAccountDetails = Partial<ICreditAccountDetails> & Pick<ICreditAccountDetails, 'id'>;

export type EntityResponseType = HttpResponse<ICreditAccountDetails>;
export type EntityArrayResponseType = HttpResponse<ICreditAccountDetails[]>;

@Injectable({ providedIn: 'root' })
export class CreditAccountDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/credit-account-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(creditAccountDetails: NewCreditAccountDetails): Observable<EntityResponseType> {
    return this.http.post<ICreditAccountDetails>(this.resourceUrl, creditAccountDetails, { observe: 'response' });
  }

  update(creditAccountDetails: ICreditAccountDetails): Observable<EntityResponseType> {
    return this.http.put<ICreditAccountDetails>(
      `${this.resourceUrl}/${this.getCreditAccountDetailsIdentifier(creditAccountDetails)}`,
      creditAccountDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(creditAccountDetails: PartialUpdateCreditAccountDetails): Observable<EntityResponseType> {
    return this.http.patch<ICreditAccountDetails>(
      `${this.resourceUrl}/${this.getCreditAccountDetailsIdentifier(creditAccountDetails)}`,
      creditAccountDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreditAccountDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreditAccountDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreditAccountDetailsIdentifier(creditAccountDetails: Pick<ICreditAccountDetails, 'id'>): number {
    return creditAccountDetails.id;
  }

  compareCreditAccountDetails(o1: Pick<ICreditAccountDetails, 'id'> | null, o2: Pick<ICreditAccountDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreditAccountDetailsIdentifier(o1) === this.getCreditAccountDetailsIdentifier(o2) : o1 === o2;
  }

  addCreditAccountDetailsToCollectionIfMissing<Type extends Pick<ICreditAccountDetails, 'id'>>(
    creditAccountDetailsCollection: Type[],
    ...creditAccountDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const creditAccountDetails: Type[] = creditAccountDetailsToCheck.filter(isPresent);
    if (creditAccountDetails.length > 0) {
      const creditAccountDetailsCollectionIdentifiers = creditAccountDetailsCollection.map(
        creditAccountDetailsItem => this.getCreditAccountDetailsIdentifier(creditAccountDetailsItem)!,
      );
      const creditAccountDetailsToAdd = creditAccountDetails.filter(creditAccountDetailsItem => {
        const creditAccountDetailsIdentifier = this.getCreditAccountDetailsIdentifier(creditAccountDetailsItem);
        if (creditAccountDetailsCollectionIdentifiers.includes(creditAccountDetailsIdentifier)) {
          return false;
        }
        creditAccountDetailsCollectionIdentifiers.push(creditAccountDetailsIdentifier);
        return true;
      });
      return [...creditAccountDetailsToAdd, ...creditAccountDetailsCollection];
    }
    return creditAccountDetailsCollection;
  }
}
