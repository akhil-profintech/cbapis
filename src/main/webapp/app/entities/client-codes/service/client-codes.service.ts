import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IClientCodes, NewClientCodes } from '../client-codes.model';

export type PartialUpdateClientCodes = Partial<IClientCodes> & Pick<IClientCodes, 'id'>;

export type EntityResponseType = HttpResponse<IClientCodes>;
export type EntityArrayResponseType = HttpResponse<IClientCodes[]>;

@Injectable({ providedIn: 'root' })
export class ClientCodesService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/client-codes');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(clientCodes: NewClientCodes): Observable<EntityResponseType> {
    return this.http.post<IClientCodes>(this.resourceUrl, clientCodes, { observe: 'response' });
  }

  update(clientCodes: IClientCodes): Observable<EntityResponseType> {
    return this.http.put<IClientCodes>(`${this.resourceUrl}/${this.getClientCodesIdentifier(clientCodes)}`, clientCodes, {
      observe: 'response',
    });
  }

  partialUpdate(clientCodes: PartialUpdateClientCodes): Observable<EntityResponseType> {
    return this.http.patch<IClientCodes>(`${this.resourceUrl}/${this.getClientCodesIdentifier(clientCodes)}`, clientCodes, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClientCodes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClientCodes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClientCodesIdentifier(clientCodes: Pick<IClientCodes, 'id'>): number {
    return clientCodes.id;
  }

  compareClientCodes(o1: Pick<IClientCodes, 'id'> | null, o2: Pick<IClientCodes, 'id'> | null): boolean {
    return o1 && o2 ? this.getClientCodesIdentifier(o1) === this.getClientCodesIdentifier(o2) : o1 === o2;
  }

  addClientCodesToCollectionIfMissing<Type extends Pick<IClientCodes, 'id'>>(
    clientCodesCollection: Type[],
    ...clientCodesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const clientCodes: Type[] = clientCodesToCheck.filter(isPresent);
    if (clientCodes.length > 0) {
      const clientCodesCollectionIdentifiers = clientCodesCollection.map(
        clientCodesItem => this.getClientCodesIdentifier(clientCodesItem)!,
      );
      const clientCodesToAdd = clientCodes.filter(clientCodesItem => {
        const clientCodesIdentifier = this.getClientCodesIdentifier(clientCodesItem);
        if (clientCodesCollectionIdentifiers.includes(clientCodesIdentifier)) {
          return false;
        }
        clientCodesCollectionIdentifiers.push(clientCodesIdentifier);
        return true;
      });
      return [...clientCodesToAdd, ...clientCodesCollection];
    }
    return clientCodesCollection;
  }
}
