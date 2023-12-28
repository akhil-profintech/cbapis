import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProspectRequest, NewProspectRequest } from '../prospect-request.model';

export type PartialUpdateProspectRequest = Partial<IProspectRequest> & Pick<IProspectRequest, 'id'>;

type RestOf<T extends IProspectRequest | NewProspectRequest> = Omit<T, 'prospectRequestDate'> & {
  prospectRequestDate?: string | null;
};

export type RestProspectRequest = RestOf<IProspectRequest>;

export type NewRestProspectRequest = RestOf<NewProspectRequest>;

export type PartialUpdateRestProspectRequest = RestOf<PartialUpdateProspectRequest>;

export type EntityResponseType = HttpResponse<IProspectRequest>;
export type EntityArrayResponseType = HttpResponse<IProspectRequest[]>;

@Injectable({ providedIn: 'root' })
export class ProspectRequestService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/prospect-requests');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(prospectRequest: NewProspectRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prospectRequest);
    return this.http
      .post<RestProspectRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(prospectRequest: IProspectRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prospectRequest);
    return this.http
      .put<RestProspectRequest>(`${this.resourceUrl}/${this.getProspectRequestIdentifier(prospectRequest)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(prospectRequest: PartialUpdateProspectRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prospectRequest);
    return this.http
      .patch<RestProspectRequest>(`${this.resourceUrl}/${this.getProspectRequestIdentifier(prospectRequest)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestProspectRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestProspectRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getProspectRequestIdentifier(prospectRequest: Pick<IProspectRequest, 'id'>): number {
    return prospectRequest.id;
  }

  compareProspectRequest(o1: Pick<IProspectRequest, 'id'> | null, o2: Pick<IProspectRequest, 'id'> | null): boolean {
    return o1 && o2 ? this.getProspectRequestIdentifier(o1) === this.getProspectRequestIdentifier(o2) : o1 === o2;
  }

  addProspectRequestToCollectionIfMissing<Type extends Pick<IProspectRequest, 'id'>>(
    prospectRequestCollection: Type[],
    ...prospectRequestsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const prospectRequests: Type[] = prospectRequestsToCheck.filter(isPresent);
    if (prospectRequests.length > 0) {
      const prospectRequestCollectionIdentifiers = prospectRequestCollection.map(
        prospectRequestItem => this.getProspectRequestIdentifier(prospectRequestItem)!,
      );
      const prospectRequestsToAdd = prospectRequests.filter(prospectRequestItem => {
        const prospectRequestIdentifier = this.getProspectRequestIdentifier(prospectRequestItem);
        if (prospectRequestCollectionIdentifiers.includes(prospectRequestIdentifier)) {
          return false;
        }
        prospectRequestCollectionIdentifiers.push(prospectRequestIdentifier);
        return true;
      });
      return [...prospectRequestsToAdd, ...prospectRequestCollection];
    }
    return prospectRequestCollection;
  }

  protected convertDateFromClient<T extends IProspectRequest | NewProspectRequest | PartialUpdateProspectRequest>(
    prospectRequest: T,
  ): RestOf<T> {
    return {
      ...prospectRequest,
      prospectRequestDate: prospectRequest.prospectRequestDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restProspectRequest: RestProspectRequest): IProspectRequest {
    return {
      ...restProspectRequest,
      prospectRequestDate: restProspectRequest.prospectRequestDate ? dayjs(restProspectRequest.prospectRequestDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestProspectRequest>): HttpResponse<IProspectRequest> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestProspectRequest[]>): HttpResponse<IProspectRequest[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
