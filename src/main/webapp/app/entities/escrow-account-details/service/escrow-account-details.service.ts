import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEscrowAccountDetails, NewEscrowAccountDetails } from '../escrow-account-details.model';

export type PartialUpdateEscrowAccountDetails = Partial<IEscrowAccountDetails> & Pick<IEscrowAccountDetails, 'id'>;

export type EntityResponseType = HttpResponse<IEscrowAccountDetails>;
export type EntityArrayResponseType = HttpResponse<IEscrowAccountDetails[]>;

@Injectable({ providedIn: 'root' })
export class EscrowAccountDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/escrow-account-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(escrowAccountDetails: NewEscrowAccountDetails): Observable<EntityResponseType> {
    return this.http.post<IEscrowAccountDetails>(this.resourceUrl, escrowAccountDetails, { observe: 'response' });
  }

  update(escrowAccountDetails: IEscrowAccountDetails): Observable<EntityResponseType> {
    return this.http.put<IEscrowAccountDetails>(
      `${this.resourceUrl}/${this.getEscrowAccountDetailsIdentifier(escrowAccountDetails)}`,
      escrowAccountDetails,
      { observe: 'response' },
    );
  }

  partialUpdate(escrowAccountDetails: PartialUpdateEscrowAccountDetails): Observable<EntityResponseType> {
    return this.http.patch<IEscrowAccountDetails>(
      `${this.resourceUrl}/${this.getEscrowAccountDetailsIdentifier(escrowAccountDetails)}`,
      escrowAccountDetails,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEscrowAccountDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEscrowAccountDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEscrowAccountDetailsIdentifier(escrowAccountDetails: Pick<IEscrowAccountDetails, 'id'>): number {
    return escrowAccountDetails.id;
  }

  compareEscrowAccountDetails(o1: Pick<IEscrowAccountDetails, 'id'> | null, o2: Pick<IEscrowAccountDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getEscrowAccountDetailsIdentifier(o1) === this.getEscrowAccountDetailsIdentifier(o2) : o1 === o2;
  }

  addEscrowAccountDetailsToCollectionIfMissing<Type extends Pick<IEscrowAccountDetails, 'id'>>(
    escrowAccountDetailsCollection: Type[],
    ...escrowAccountDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const escrowAccountDetails: Type[] = escrowAccountDetailsToCheck.filter(isPresent);
    if (escrowAccountDetails.length > 0) {
      const escrowAccountDetailsCollectionIdentifiers = escrowAccountDetailsCollection.map(
        escrowAccountDetailsItem => this.getEscrowAccountDetailsIdentifier(escrowAccountDetailsItem)!,
      );
      const escrowAccountDetailsToAdd = escrowAccountDetails.filter(escrowAccountDetailsItem => {
        const escrowAccountDetailsIdentifier = this.getEscrowAccountDetailsIdentifier(escrowAccountDetailsItem);
        if (escrowAccountDetailsCollectionIdentifiers.includes(escrowAccountDetailsIdentifier)) {
          return false;
        }
        escrowAccountDetailsCollectionIdentifiers.push(escrowAccountDetailsIdentifier);
        return true;
      });
      return [...escrowAccountDetailsToAdd, ...escrowAccountDetailsCollection];
    }
    return escrowAccountDetailsCollection;
  }
}
