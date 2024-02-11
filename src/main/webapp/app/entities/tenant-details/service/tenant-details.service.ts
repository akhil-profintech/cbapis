import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITenantDetails, NewTenantDetails } from '../tenant-details.model';

export type PartialUpdateTenantDetails = Partial<ITenantDetails> & Pick<ITenantDetails, 'id'>;

export type EntityResponseType = HttpResponse<ITenantDetails>;
export type EntityArrayResponseType = HttpResponse<ITenantDetails[]>;

@Injectable({ providedIn: 'root' })
export class TenantDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tenant-details');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(tenantDetails: NewTenantDetails): Observable<EntityResponseType> {
    return this.http.post<ITenantDetails>(this.resourceUrl, tenantDetails, { observe: 'response' });
  }

  update(tenantDetails: ITenantDetails): Observable<EntityResponseType> {
    return this.http.put<ITenantDetails>(`${this.resourceUrl}/${this.getTenantDetailsIdentifier(tenantDetails)}`, tenantDetails, {
      observe: 'response',
    });
  }

  partialUpdate(tenantDetails: PartialUpdateTenantDetails): Observable<EntityResponseType> {
    return this.http.patch<ITenantDetails>(`${this.resourceUrl}/${this.getTenantDetailsIdentifier(tenantDetails)}`, tenantDetails, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITenantDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITenantDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTenantDetailsIdentifier(tenantDetails: Pick<ITenantDetails, 'id'>): number {
    return tenantDetails.id;
  }

  compareTenantDetails(o1: Pick<ITenantDetails, 'id'> | null, o2: Pick<ITenantDetails, 'id'> | null): boolean {
    return o1 && o2 ? this.getTenantDetailsIdentifier(o1) === this.getTenantDetailsIdentifier(o2) : o1 === o2;
  }

  addTenantDetailsToCollectionIfMissing<Type extends Pick<ITenantDetails, 'id'>>(
    tenantDetailsCollection: Type[],
    ...tenantDetailsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tenantDetails: Type[] = tenantDetailsToCheck.filter(isPresent);
    if (tenantDetails.length > 0) {
      const tenantDetailsCollectionIdentifiers = tenantDetailsCollection.map(
        tenantDetailsItem => this.getTenantDetailsIdentifier(tenantDetailsItem)!,
      );
      const tenantDetailsToAdd = tenantDetails.filter(tenantDetailsItem => {
        const tenantDetailsIdentifier = this.getTenantDetailsIdentifier(tenantDetailsItem);
        if (tenantDetailsCollectionIdentifiers.includes(tenantDetailsIdentifier)) {
          return false;
        }
        tenantDetailsCollectionIdentifiers.push(tenantDetailsIdentifier);
        return true;
      });
      return [...tenantDetailsToAdd, ...tenantDetailsCollection];
    }
    return tenantDetailsCollection;
  }
}
