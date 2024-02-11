import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUserDtls } from '../user-dtls.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../user-dtls.test-samples';

import { UserDtlsService } from './user-dtls.service';

const requireRestSample: IUserDtls = {
  ...sampleWithRequiredData,
};

describe('UserDtls Service', () => {
  let service: UserDtlsService;
  let httpMock: HttpTestingController;
  let expectedResult: IUserDtls | IUserDtls[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserDtlsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a UserDtls', () => {
      const userDtls = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(userDtls).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a UserDtls', () => {
      const userDtls = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(userDtls).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a UserDtls', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of UserDtls', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a UserDtls', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addUserDtlsToCollectionIfMissing', () => {
      it('should add a UserDtls to an empty array', () => {
        const userDtls: IUserDtls = sampleWithRequiredData;
        expectedResult = service.addUserDtlsToCollectionIfMissing([], userDtls);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userDtls);
      });

      it('should not add a UserDtls to an array that contains it', () => {
        const userDtls: IUserDtls = sampleWithRequiredData;
        const userDtlsCollection: IUserDtls[] = [
          {
            ...userDtls,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUserDtlsToCollectionIfMissing(userDtlsCollection, userDtls);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserDtls to an array that doesn't contain it", () => {
        const userDtls: IUserDtls = sampleWithRequiredData;
        const userDtlsCollection: IUserDtls[] = [sampleWithPartialData];
        expectedResult = service.addUserDtlsToCollectionIfMissing(userDtlsCollection, userDtls);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userDtls);
      });

      it('should add only unique UserDtls to an array', () => {
        const userDtlsArray: IUserDtls[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const userDtlsCollection: IUserDtls[] = [sampleWithRequiredData];
        expectedResult = service.addUserDtlsToCollectionIfMissing(userDtlsCollection, ...userDtlsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userDtls: IUserDtls = sampleWithRequiredData;
        const userDtls2: IUserDtls = sampleWithPartialData;
        expectedResult = service.addUserDtlsToCollectionIfMissing([], userDtls, userDtls2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userDtls);
        expect(expectedResult).toContain(userDtls2);
      });

      it('should accept null and undefined values', () => {
        const userDtls: IUserDtls = sampleWithRequiredData;
        expectedResult = service.addUserDtlsToCollectionIfMissing([], null, userDtls, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userDtls);
      });

      it('should return initial array if no UserDtls is added', () => {
        const userDtlsCollection: IUserDtls[] = [sampleWithRequiredData];
        expectedResult = service.addUserDtlsToCollectionIfMissing(userDtlsCollection, undefined, null);
        expect(expectedResult).toEqual(userDtlsCollection);
      });
    });

    describe('compareUserDtls', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUserDtls(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUserDtls(entity1, entity2);
        const compareResult2 = service.compareUserDtls(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUserDtls(entity1, entity2);
        const compareResult2 = service.compareUserDtls(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUserDtls(entity1, entity2);
        const compareResult2 = service.compareUserDtls(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
