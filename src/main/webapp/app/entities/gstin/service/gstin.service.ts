import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGstin, NewGstin } from '../gstin.model';

export type PartialUpdateGstin = Partial<IGstin> & Pick<IGstin, 'id'>;

export type EntityResponseType = HttpResponse<IGstin>;
export type EntityArrayResponseType = HttpResponse<IGstin[]>;

@Injectable({ providedIn: 'root' })
export class GstinService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/gstins');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(gstin: NewGstin): Observable<EntityResponseType> {
    return this.http.post<IGstin>(this.resourceUrl, gstin, { observe: 'response' });
  }

  update(gstin: IGstin): Observable<EntityResponseType> {
    return this.http.put<IGstin>(`${this.resourceUrl}/${this.getGstinIdentifier(gstin)}`, gstin, { observe: 'response' });
  }

  partialUpdate(gstin: PartialUpdateGstin): Observable<EntityResponseType> {
    return this.http.patch<IGstin>(`${this.resourceUrl}/${this.getGstinIdentifier(gstin)}`, gstin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGstin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGstin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getGstinIdentifier(gstin: Pick<IGstin, 'id'>): number {
    return gstin.id;
  }

  compareGstin(o1: Pick<IGstin, 'id'> | null, o2: Pick<IGstin, 'id'> | null): boolean {
    return o1 && o2 ? this.getGstinIdentifier(o1) === this.getGstinIdentifier(o2) : o1 === o2;
  }

  addGstinToCollectionIfMissing<Type extends Pick<IGstin, 'id'>>(
    gstinCollection: Type[],
    ...gstinsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const gstins: Type[] = gstinsToCheck.filter(isPresent);
    if (gstins.length > 0) {
      const gstinCollectionIdentifiers = gstinCollection.map(gstinItem => this.getGstinIdentifier(gstinItem)!);
      const gstinsToAdd = gstins.filter(gstinItem => {
        const gstinIdentifier = this.getGstinIdentifier(gstinItem);
        if (gstinCollectionIdentifiers.includes(gstinIdentifier)) {
          return false;
        }
        gstinCollectionIdentifiers.push(gstinIdentifier);
        return true;
      });
      return [...gstinsToAdd, ...gstinCollection];
    }
    return gstinCollection;
  }
}
