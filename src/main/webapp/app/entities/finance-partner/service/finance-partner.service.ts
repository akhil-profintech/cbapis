import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFinancePartner, NewFinancePartner } from '../finance-partner.model';

export type PartialUpdateFinancePartner = Partial<IFinancePartner> & Pick<IFinancePartner, 'id'>;

export type EntityResponseType = HttpResponse<IFinancePartner>;
export type EntityArrayResponseType = HttpResponse<IFinancePartner[]>;

@Injectable({ providedIn: 'root' })
export class FinancePartnerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/finance-partners');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(financePartner: NewFinancePartner): Observable<EntityResponseType> {
    return this.http.post<IFinancePartner>(this.resourceUrl, financePartner, { observe: 'response' });
  }

  update(financePartner: IFinancePartner): Observable<EntityResponseType> {
    return this.http.put<IFinancePartner>(`${this.resourceUrl}/${this.getFinancePartnerIdentifier(financePartner)}`, financePartner, {
      observe: 'response',
    });
  }

  partialUpdate(financePartner: PartialUpdateFinancePartner): Observable<EntityResponseType> {
    return this.http.patch<IFinancePartner>(`${this.resourceUrl}/${this.getFinancePartnerIdentifier(financePartner)}`, financePartner, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFinancePartner>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFinancePartner[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFinancePartnerIdentifier(financePartner: Pick<IFinancePartner, 'id'>): number {
    return financePartner.id;
  }

  compareFinancePartner(o1: Pick<IFinancePartner, 'id'> | null, o2: Pick<IFinancePartner, 'id'> | null): boolean {
    return o1 && o2 ? this.getFinancePartnerIdentifier(o1) === this.getFinancePartnerIdentifier(o2) : o1 === o2;
  }

  addFinancePartnerToCollectionIfMissing<Type extends Pick<IFinancePartner, 'id'>>(
    financePartnerCollection: Type[],
    ...financePartnersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const financePartners: Type[] = financePartnersToCheck.filter(isPresent);
    if (financePartners.length > 0) {
      const financePartnerCollectionIdentifiers = financePartnerCollection.map(
        financePartnerItem => this.getFinancePartnerIdentifier(financePartnerItem)!,
      );
      const financePartnersToAdd = financePartners.filter(financePartnerItem => {
        const financePartnerIdentifier = this.getFinancePartnerIdentifier(financePartnerItem);
        if (financePartnerCollectionIdentifiers.includes(financePartnerIdentifier)) {
          return false;
        }
        financePartnerCollectionIdentifiers.push(financePartnerIdentifier);
        return true;
      });
      return [...financePartnersToAdd, ...financePartnerCollection];
    }
    return financePartnerCollection;
  }
}
