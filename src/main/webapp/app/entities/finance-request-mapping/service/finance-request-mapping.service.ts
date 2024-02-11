import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFinanceRequestMapping, NewFinanceRequestMapping } from '../finance-request-mapping.model';

export type PartialUpdateFinanceRequestMapping = Partial<IFinanceRequestMapping> & Pick<IFinanceRequestMapping, 'id'>;

export type EntityResponseType = HttpResponse<IFinanceRequestMapping>;
export type EntityArrayResponseType = HttpResponse<IFinanceRequestMapping[]>;

@Injectable({ providedIn: 'root' })
export class FinanceRequestMappingService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/finance-request-mappings');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(financeRequestMapping: NewFinanceRequestMapping): Observable<EntityResponseType> {
    return this.http.post<IFinanceRequestMapping>(this.resourceUrl, financeRequestMapping, { observe: 'response' });
  }

  update(financeRequestMapping: IFinanceRequestMapping): Observable<EntityResponseType> {
    return this.http.put<IFinanceRequestMapping>(
      `${this.resourceUrl}/${this.getFinanceRequestMappingIdentifier(financeRequestMapping)}`,
      financeRequestMapping,
      { observe: 'response' },
    );
  }

  partialUpdate(financeRequestMapping: PartialUpdateFinanceRequestMapping): Observable<EntityResponseType> {
    return this.http.patch<IFinanceRequestMapping>(
      `${this.resourceUrl}/${this.getFinanceRequestMappingIdentifier(financeRequestMapping)}`,
      financeRequestMapping,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFinanceRequestMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFinanceRequestMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFinanceRequestMappingIdentifier(financeRequestMapping: Pick<IFinanceRequestMapping, 'id'>): number {
    return financeRequestMapping.id;
  }

  compareFinanceRequestMapping(o1: Pick<IFinanceRequestMapping, 'id'> | null, o2: Pick<IFinanceRequestMapping, 'id'> | null): boolean {
    return o1 && o2 ? this.getFinanceRequestMappingIdentifier(o1) === this.getFinanceRequestMappingIdentifier(o2) : o1 === o2;
  }

  addFinanceRequestMappingToCollectionIfMissing<Type extends Pick<IFinanceRequestMapping, 'id'>>(
    financeRequestMappingCollection: Type[],
    ...financeRequestMappingsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const financeRequestMappings: Type[] = financeRequestMappingsToCheck.filter(isPresent);
    if (financeRequestMappings.length > 0) {
      const financeRequestMappingCollectionIdentifiers = financeRequestMappingCollection.map(
        financeRequestMappingItem => this.getFinanceRequestMappingIdentifier(financeRequestMappingItem)!,
      );
      const financeRequestMappingsToAdd = financeRequestMappings.filter(financeRequestMappingItem => {
        const financeRequestMappingIdentifier = this.getFinanceRequestMappingIdentifier(financeRequestMappingItem);
        if (financeRequestMappingCollectionIdentifiers.includes(financeRequestMappingIdentifier)) {
          return false;
        }
        financeRequestMappingCollectionIdentifiers.push(financeRequestMappingIdentifier);
        return true;
      });
      return [...financeRequestMappingsToAdd, ...financeRequestMappingCollection];
    }
    return financeRequestMappingCollection;
  }
}
