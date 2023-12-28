import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreditBazaarIntegrator, NewCreditBazaarIntegrator } from '../credit-bazaar-integrator.model';

export type PartialUpdateCreditBazaarIntegrator = Partial<ICreditBazaarIntegrator> & Pick<ICreditBazaarIntegrator, 'id'>;

export type EntityResponseType = HttpResponse<ICreditBazaarIntegrator>;
export type EntityArrayResponseType = HttpResponse<ICreditBazaarIntegrator[]>;

@Injectable({ providedIn: 'root' })
export class CreditBazaarIntegratorService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/credit-bazaar-integrators');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(creditBazaarIntegrator: NewCreditBazaarIntegrator): Observable<EntityResponseType> {
    return this.http.post<ICreditBazaarIntegrator>(this.resourceUrl, creditBazaarIntegrator, { observe: 'response' });
  }

  update(creditBazaarIntegrator: ICreditBazaarIntegrator): Observable<EntityResponseType> {
    return this.http.put<ICreditBazaarIntegrator>(
      `${this.resourceUrl}/${this.getCreditBazaarIntegratorIdentifier(creditBazaarIntegrator)}`,
      creditBazaarIntegrator,
      { observe: 'response' },
    );
  }

  partialUpdate(creditBazaarIntegrator: PartialUpdateCreditBazaarIntegrator): Observable<EntityResponseType> {
    return this.http.patch<ICreditBazaarIntegrator>(
      `${this.resourceUrl}/${this.getCreditBazaarIntegratorIdentifier(creditBazaarIntegrator)}`,
      creditBazaarIntegrator,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreditBazaarIntegrator>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreditBazaarIntegrator[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreditBazaarIntegratorIdentifier(creditBazaarIntegrator: Pick<ICreditBazaarIntegrator, 'id'>): number {
    return creditBazaarIntegrator.id;
  }

  compareCreditBazaarIntegrator(o1: Pick<ICreditBazaarIntegrator, 'id'> | null, o2: Pick<ICreditBazaarIntegrator, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreditBazaarIntegratorIdentifier(o1) === this.getCreditBazaarIntegratorIdentifier(o2) : o1 === o2;
  }

  addCreditBazaarIntegratorToCollectionIfMissing<Type extends Pick<ICreditBazaarIntegrator, 'id'>>(
    creditBazaarIntegratorCollection: Type[],
    ...creditBazaarIntegratorsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const creditBazaarIntegrators: Type[] = creditBazaarIntegratorsToCheck.filter(isPresent);
    if (creditBazaarIntegrators.length > 0) {
      const creditBazaarIntegratorCollectionIdentifiers = creditBazaarIntegratorCollection.map(
        creditBazaarIntegratorItem => this.getCreditBazaarIntegratorIdentifier(creditBazaarIntegratorItem)!,
      );
      const creditBazaarIntegratorsToAdd = creditBazaarIntegrators.filter(creditBazaarIntegratorItem => {
        const creditBazaarIntegratorIdentifier = this.getCreditBazaarIntegratorIdentifier(creditBazaarIntegratorItem);
        if (creditBazaarIntegratorCollectionIdentifiers.includes(creditBazaarIntegratorIdentifier)) {
          return false;
        }
        creditBazaarIntegratorCollectionIdentifiers.push(creditBazaarIntegratorIdentifier);
        return true;
      });
      return [...creditBazaarIntegratorsToAdd, ...creditBazaarIntegratorCollection];
    }
    return creditBazaarIntegratorCollection;
  }
}
