import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnchorTraderPartnerDetailComponent } from './anchor-trader-partner-detail.component';

describe('AnchorTraderPartner Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnchorTraderPartnerDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AnchorTraderPartnerDetailComponent,
              resolve: { anchorTraderPartner: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AnchorTraderPartnerDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load anchorTraderPartner on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AnchorTraderPartnerDetailComponent);

      // THEN
      expect(instance.anchorTraderPartner).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
