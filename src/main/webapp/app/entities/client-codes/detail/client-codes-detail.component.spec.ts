import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ClientCodesDetailComponent } from './client-codes-detail.component';

describe('ClientCodes Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientCodesDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ClientCodesDetailComponent,
              resolve: { clientCodes: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ClientCodesDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load clientCodes on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ClientCodesDetailComponent);

      // THEN
      expect(instance.clientCodes).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
